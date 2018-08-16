package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.JzzData;
import com.hywa.pricepublish.dao.entity.JzzDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JzzDataMapper {

    int countByExample(JzzDataExample example);

    int deleteByExample(JzzDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(JzzData record);

    int insertSelective(JzzData record);

    List<JzzData> selectByExample(JzzDataExample example);

    JzzData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JzzData record, @Param("example") JzzDataExample example);

    int updateByExample(@Param("record") JzzData record, @Param("example") JzzDataExample example);

    int updateByPrimaryKeySelective(JzzData record);

    int updateByPrimaryKey(JzzData record);

    List<Map<String,Object>> loggingData();
}