package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Dict;
import com.hywa.pricepublish.dao.entity.DictExample;
import java.util.List;

import com.hywa.pricepublish.representation.DictRep;
import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    int countByExample(DictExample example);

    int deleteByExample(DictExample example);

    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    List<Dict> selectByDictType(String dictTypeId);
}