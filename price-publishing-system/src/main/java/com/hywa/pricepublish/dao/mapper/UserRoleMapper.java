package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.UserRoleExample;
import com.hywa.pricepublish.dao.entity.UserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    List<UserRoleKey> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);

    void deleteByUserId(@Param("userId") String userId);

    void deleteByRoleId(String roleId);

    void insertBatch(List<UserRoleKey> userRoleKeys);
}