package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PrContent;
import com.hywa.pricepublish.dao.entity.PrContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrContentMapper {
    int countByExample(PrContentExample example);

    int deleteByExample(PrContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrContent record);

    int insertSelective(PrContent record);

    List<PrContent> selectByExampleWithBLOBs(PrContentExample example);

    List<PrContent> selectByExample(PrContentExample example);

    PrContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrContent record, @Param("example") PrContentExample example);

    int updateByExampleWithBLOBs(@Param("record") PrContent record, @Param("example") PrContentExample example);

    int updateByExample(@Param("record") PrContent record, @Param("example") PrContentExample example);

    int updateByPrimaryKeySelective(PrContent record);

    int updateByPrimaryKeyWithBLOBs(PrContent record);

    int updateByPrimaryKey(PrContent record);
}