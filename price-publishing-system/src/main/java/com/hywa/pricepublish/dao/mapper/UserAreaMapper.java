package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.*;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAreaMapper {
    int countByExample(UserAreaExample example);

    int deleteByExample(UserAreaExample example);

    int deleteByPrimaryKey(UserAreaKey key);

    int insert(UserArea record);

    int insertSelective(UserArea record);

    List<UserArea> selectByExample(UserAreaExample example);

    UserArea selectByPrimaryKey(UserAreaKey key);

    int updateByExampleSelective(@Param("record") UserArea record, @Param("example") UserAreaExample example);

    int updateByExample(@Param("record") UserArea record, @Param("example") UserAreaExample example);

    int updateByPrimaryKeySelective(UserArea record);

    int updateByPrimaryKey(UserArea record);

    void deleteByUserId(String userId);

    UserArea selectByUserId(String userId);

    Address selectRegion(String tableName, String regionId);

    void insertBatch(List<UserArea> userAreas);

}