package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PrInformer;
import com.hywa.pricepublish.dao.entity.PrInformerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrInformerMapper {
    int countByExample(PrInformerExample example);

    int deleteByExample(PrInformerExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrInformer record);

    int insertSelective(PrInformer record);

    List<PrInformer> selectByExample(PrInformerExample example);

    PrInformer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrInformer record, @Param("example") PrInformerExample example);

    int updateByExample(@Param("record") PrInformer record, @Param("example") PrInformerExample example);

    int updateByPrimaryKeySelective(PrInformer record);

    int updateByPrimaryKey(PrInformer record);
}