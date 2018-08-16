package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.RoleMenuExample;
import com.hywa.pricepublish.dao.entity.RoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    int countByExample(RoleMenuExample example);

    int deleteByExample(RoleMenuExample example);

    int deleteByRoleId(String roleId);

    int insert(RoleMenuKey record);

    int insertList(@Param("keys")List<String> keys,@Param("roleId") String id);

    int insertSelective(RoleMenuKey record);

    List<RoleMenuKey> selectByExample(RoleMenuExample example);

    int updateByExampleSelective(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);

    int updateByExample(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);

    int selectCountByRoleId(@Param("roleId")String roleId);

    void insertBatch(List<RoleMenuKey> roleMenuKeys);
}