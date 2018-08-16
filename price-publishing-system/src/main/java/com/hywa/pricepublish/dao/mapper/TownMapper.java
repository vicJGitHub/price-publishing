package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Town;
import com.hywa.pricepublish.dao.entity.TownExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TownMapper {
    int countByExample(TownExample example);

    int deleteByExample(TownExample example);

    int deleteByPrimaryKey(String id);

    int insert(Town record);

    int insertSelective(Town record);

    List<Town> selectByExample(TownExample example);

    Town selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Town record, @Param("example") TownExample example);

    int updateByExample(@Param("record") Town record, @Param("example") TownExample example);

    int updateByPrimaryKeySelective(Town record);

    int updateByPrimaryKey(Town record);
}