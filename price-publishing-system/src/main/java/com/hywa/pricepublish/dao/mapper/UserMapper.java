package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.User;
import com.hywa.pricepublish.dao.entity.UserExample;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.Region;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //此处sql是错的,存在同名情况会查处一个list(通过在新增时限制名称和电话)
    User selectByUserName(String userName);

    List<User> selectByRegionAndWorkUnit(@Param("name") String name,@Param("region") String region, @Param("workUnit")String workUnit);

    int deleteByUserId(String userId);

    Map<String,Object> selectByIdUserArea(@Param(value = "id") String id,@Param(value = "type") int type);

    Map<String,Object> selectByIdUserInfo(@Param(value = "id") String id);
}