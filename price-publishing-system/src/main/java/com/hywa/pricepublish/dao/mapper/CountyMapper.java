package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.County;
import com.hywa.pricepublish.dao.entity.CountyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountyMapper {
    int countByExample(CountyExample example);

    int deleteByExample(CountyExample example);

    int deleteByPrimaryKey(String id);

    int insert(County record);

    int insertSelective(County record);

    List<County> selectByExample(CountyExample example);

    County selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") County record, @Param("example") CountyExample example);

    int updateByExample(@Param("record") County record, @Param("example") CountyExample example);

    int updateByPrimaryKeySelective(County record);

    int updateByPrimaryKey(County record);
}