package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PrInformEvent;
import com.hywa.pricepublish.dao.entity.PrInformEventExample;
import com.hywa.pricepublish.representation.PrInformRep;
import com.hywa.pricepublish.representation.PrInformReps;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrInformEventMapper {
    int countByExample(PrInformEventExample example);

    int deleteByExample(PrInformEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrInformEvent record);

    int insertSelective(PrInformEvent record);

    List<PrInformEvent> selectByExample(PrInformEventExample example);

    PrInformEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrInformEvent record, @Param("example") PrInformEventExample example);

    int updateByExample(@Param("record") PrInformEvent record, @Param("example") PrInformEventExample example);

    int updateByPrimaryKeySelective(PrInformEvent record);

    int updateByPrimaryKey(PrInformEvent record);

    PrInformRep findById(@Param("id") String id);

    List<PrInformRep> findAll(@Param("prInformRep") PrInformRep prInformRep);
}