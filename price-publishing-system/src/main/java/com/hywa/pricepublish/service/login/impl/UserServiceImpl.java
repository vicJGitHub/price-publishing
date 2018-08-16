package com.hywa.pricepublish.service.login.impl;

import static com.hywa.pricepublish.common.enums.CommonEnum.ILLEGAL_PARAMETER_ERROR;
import static com.hywa.pricepublish.common.enums.CommonEnum.PASSWORD_ERROR;
import static com.hywa.pricepublish.common.enums.CommonEnum.TELEPHONE_FORMAL_ERROR;
import static com.hywa.pricepublish.common.enums.CommonEnum.UNBOUND_REGION_ERROR;
import static com.hywa.pricepublish.common.enums.CommonEnum.USER_CANCEL;
import static com.hywa.pricepublish.common.enums.CommonEnum.USER_NAME_REPEATED;
import static com.hywa.pricepublish.common.enums.CommonEnum.USER_TELEPHONE_REPEATED;
import static com.hywa.pricepublish.common.enums.CommonEnum.USER_UNREGISTERED_ERROR;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.dao.entity.User;
import com.hywa.pricepublish.dao.entity.UserArea;
import com.hywa.pricepublish.dao.entity.UserExample;
import com.hywa.pricepublish.dao.entity.UserRoleKey;
import com.hywa.pricepublish.dao.mapper.RoleMapper;
import com.hywa.pricepublish.dao.mapper.UserAreaMapper;
import com.hywa.pricepublish.dao.mapper.UserMapper;
import com.hywa.pricepublish.dao.mapper.UserRoleMapper;
import com.hywa.pricepublish.representation.RoleRep;
import com.hywa.pricepublish.representation.RoleReps;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.representation.UserReps;
import com.hywa.pricepublish.representation.ext.UserRepExt;
import com.hywa.pricepublish.service.login.UserAreaService;
import com.hywa.pricepublish.service.login.UserRoleService;
import com.hywa.pricepublish.service.login.UserService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserAreaMapper userAreaMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    private UserAreaService userAreaService;

    @Autowired
    UserRoleService userRoleService;

    @Override
    public void save(UserRepExt userRep) {
        User user = new User(userRep);
        if (!StringUtils.isEmpty(userRep.getName()) && !nameIsEmpty(userRep.getName())) {
            throw new GlobalException(USER_NAME_REPEATED.getIndex(), USER_NAME_REPEATED.getValue());
        }
        if (!StringUtils.isEmpty(userRep.getPhone()) && !phoneIsEmpty(userRep.getPhone())) {
            throw new GlobalException(USER_TELEPHONE_REPEATED.getIndex(),
                    USER_TELEPHONE_REPEATED.getValue());
        } else if (!StringUtils.isCellPhone(userRep.getPhone())) {
            throw new GlobalException(TELEPHONE_FORMAL_ERROR.getIndex(),
                    TELEPHONE_FORMAL_ERROR.getValue());
        }
        if (!CollectionUtils.isEmpty(userRep.getRoles())) {
            List<UserRoleKey> userRoleKeys = new ArrayList<>();
            userRep.getRoles()
                    .forEach(roles -> userRoleKeys.add(new UserRoleKey(user.getId(), roles)));
            userRoleService.insertBatch(userRoleKeys);
        }
        userMapper.insert(user);
        if (!StringUtils.isEmpty(userRep.getProvinceId())) {
            String region = userRep.getProvinceId();
            short type = 1;
            if (!StringUtils.isEmpty(userRep.getCityId())) {
                if (!StringUtils.isEmpty(userRep.getCountyId())) {
                    region = userRep.getCountyId();
                    type = 3;
                } else {
                    type = 2;
                    region = userRep.getCityId();
                }
            }
            userAreaMapper.insert(new UserArea(user.getId(), region, type));
        }
    }

    @Override
    public UserRep findById(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        String regionId = userAreaService.findRegionId(user.getId());

        return new UserRep(user.getId(), user.getName(), user.getTelephone(),
                user.getSex(), user.getJob(), user.getWorkUnit(),
                user.getAge(), regionId);
    }

    @Override
    public UserReps findUsers(int pageNum, int pageSize, String name, String region, String workUnit) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<User> users = userMapper.selectByRegionAndWorkUnit(name, region, workUnit);
        PageInfo<User> page = new PageInfo<>(users);
        List<UserRep> userRepList = new ArrayList<>();
        for (User user : users) {
            UserRep userRep = new UserRep(user);
            userRepList.add(userRep);
        }
        return new UserReps(page.getTotal(), userRepList);
    }


    @Override
    public User findByName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public UserRep userLogin(String userName, String psw) {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            //用户未注册
            throw new GlobalException(USER_UNREGISTERED_ERROR.getIndex(),
                    USER_UNREGISTERED_ERROR.getValue());
        } else if (user.getPassword().equals(StringUtils.md5(psw))) {
            if (user.getIsDel() == ConstantPool.DEL) {
                //用户已经注销，请联系管理员
                throw new GlobalException(USER_CANCEL.getIndex(), USER_CANCEL.getValue());
            } else {
                String regionId = null;
                try {
                    regionId = userAreaService.findRegionId(user.getId());
                } catch (Exception e) {
                    //此账户未绑定区域，请联系管理员！
                    throw new GlobalException(
                            UNBOUND_REGION_ERROR.getIndex(),
                            UNBOUND_REGION_ERROR.getValue());
                }
                UserRep userRep = new UserRep(user.getId(), user.getName(), user.getTelephone(),
                        user.getSex(), user.getJob(), user.getWorkUnit(), user.getAge(), regionId);
                return userRep;
            }
        } else {
            //密码错误
            throw new GlobalException(PASSWORD_ERROR.getIndex(), PASSWORD_ERROR.getValue());
        }
    }

    @Override
    public boolean delete(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(ILLEGAL_PARAMETER_ERROR.getIndex(), "userId不能为空");
        }
        if(!(userMapper.deleteByUserId(userId)>0)){
            return false;
        }
        userAreaMapper.deleteByUserId(userId);
        userRoleMapper.deleteByUserId(userId);
        return true;
    }

    @Override
    public void updateUserRole(String userId, RoleReps roleReps) {
        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(ILLEGAL_PARAMETER_ERROR.getIndex(), "userId不能为空");
        }
        userRoleMapper.deleteByUserId(userId);
        List<UserRoleKey> userRoleKeys = new ArrayList<>();
        for (RoleRep roleRep : roleReps.getList()) {
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setRoleId(roleRep.getId());
            userRoleKey.setUserId(userId);
            userRoleKeys.add(userRoleKey);
        }
        userRoleMapper.insertBatch(userRoleKeys);
    }

    @Override
    public boolean nameIsEmpty(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andIsDelEqualTo(ConstantPool.NOT_DEL);
        return userMapper.countByExample(userExample) > 0 ? false : true;
    }

    @Override
    public boolean phoneIsEmpty(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTelephoneEqualTo(phone);
        criteria.andIsDelEqualTo(ConstantPool.NOT_DEL);
        return userMapper.countByExample(userExample) > 0 ? false : true;
    }

    @Override
    public Map<String, Object> findAllById(String id) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> map = new HashMap<>();
        map = userMapper.selectByIdUserInfo(id);
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        map.putAll(userMapper.selectByIdUserArea((String) map.get("region_id"), (int) map.get("type")));
        return reMap(map);
    }

    //转换格式
    private Map<String,Object> reMap( Map<String, Object> map){

        Map<String,Object> reField =new HashMap<String, Object>();//返回字段
        reField.put("id",map.get("id"));//主键
        reField.put("name",map.get("name"));//姓名
        reField.put("sex",1==(int)map.get("sex")?"男":"女");//1男 2女
        reField.put("phone",map.get("telephone"));//电话
        reField.put("account",map.get("account"));//MD5加密
        reField.put("email",map.get("email"));//邮箱号码
        reField.put("updateUser",map.get("update_user"));//更新人
        reField.put("createUser",map.get("create_user"));//创建人
        reField.put("isDel",map.get("is_del"));//0 未删 1 删除
        reField.put("jobTitle",map.get("job"));//职务
        reField.put("type",map.get("type"));//0 未删 1 删除
        reField.put("region",map.get("region_id"));//
        reField.put("workUnit",map.get("work_unit"));//工作单位
        reField.put("age",map.get("age"));//年龄
        reField.put("countyId",map.get("countyId"));//
        reField.put("cityId",map.get("cityId"));//
        reField.put("provinceId",map.get("provinceId"));//
        reField.put("countyName",map.get("countyName"));//
        reField.put("cityName",map.get("cityName"));//
        reField.put("provinceName",map.get("provinceName"));//
        reField.put("roles",map.get("roles"));//
        return reField;//返回字段
    }

}
