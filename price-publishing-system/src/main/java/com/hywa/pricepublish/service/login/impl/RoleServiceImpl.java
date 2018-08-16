package com.hywa.pricepublish.service.login.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Role;
import com.hywa.pricepublish.dao.entity.RoleMenuKey;
import com.hywa.pricepublish.dao.mapper.RoleMapper;
import com.hywa.pricepublish.dao.mapper.RoleMenuMapper;
import com.hywa.pricepublish.dao.mapper.UserRoleMapper;
import com.hywa.pricepublish.representation.MenuRep;
import com.hywa.pricepublish.representation.RoleRep;
import com.hywa.pricepublish.representation.RoleReps;
import com.hywa.pricepublish.service.login.RoleService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public void save(RoleRep roleRep) {
        Role role = new Role();
        BeanUtils.copyProperties(roleRep, role, "code", "id");
        role.setCreateTime(new Date());
        role.setId(UUIDUtils.randomUUID());
        roleMapper.insert(role);

        List<MenuRep> menuReps = roleRep.getMenuReps();
        List<RoleMenuKey> roleMenus = new ArrayList<>();
        getRoleMenus(role.getId(), menuReps, roleMenus);
        roleMenuMapper.insertBatch(roleMenus);
    }

    private void getRoleMenus(String roleId, List<MenuRep> menuReps, List<RoleMenuKey> roleMenus) {
        for (MenuRep menuRep : menuReps) {
            roleMenus.add(new RoleMenuKey(roleId, menuRep.getId()));
            if (null!=menuRep.getChildMenus()&&menuRep.getChildMenus().size() > 0) {
                getRoleMenus(roleId, menuRep.getChildMenus(), roleMenus);
            }
        }
    }

    @Override
    @Transactional
    public int update(RoleRep roleRep) {
        roleMenuMapper.deleteByRoleId(roleRep.getId());
        List<RoleMenuKey> roleMenus = new ArrayList<>();
        getRoleMenus(roleRep.getId(), roleRep.getMenuReps(), roleMenus);
        roleMenuMapper.insertBatch(roleMenus);

        Role role = new Role();
        BeanUtils.copyProperties(roleRep, role, "code");
        role.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
        roleMenuMapper.deleteByRoleId(id);
        userRoleMapper.deleteByRoleId(id);
    }

    @Override
    public RoleReps selectAll(int pageNum, int pageSize,String name) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Role> roles = roleMapper.selectAll(name);
        List<RoleRep> roleReps = new ArrayList<>();
        roles.forEach(role -> roleReps.add(new RoleRep(role)));
        return new RoleReps(roleReps, new PageInfo<>(roles).getTotal());
    }

    @Override
    public RoleReps selectRolesByUserId(int pageNum, int pageSize, String userId) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Role> roles = roleMapper.selectByUserId(userId);
        List<RoleRep> roleReps = new ArrayList<>();
        roles.forEach(role -> roleReps.add(new RoleRep(role)));
        return new RoleReps(roleReps, new PageInfo<>(roles).getTotal());
    }
}
