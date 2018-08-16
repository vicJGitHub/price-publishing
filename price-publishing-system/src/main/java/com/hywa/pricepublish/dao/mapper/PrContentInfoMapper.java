package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PrContentInfo;
import com.hywa.pricepublish.dao.entity.PrContentInfoExample;
import com.hywa.pricepublish.representation.PrContentInfoRep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrContentInfoMapper {
    int countByExample(PrContentInfoExample example);

    int deleteByExample(PrContentInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrContentInfo record);

    int insertSelective(PrContentInfo record);

    List<PrContentInfo> selectByExample(PrContentInfoExample example);

    PrContentInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrContentInfo record, @Param("example") PrContentInfoExample example);

    int updateByExample(@Param("record") PrContentInfo record, @Param("example") PrContentInfoExample example);

    int updateByPrimaryKeySelective(PrContentInfo record);

    int updateByPrimaryKey(PrContentInfo record);

        PrContentInfoRep findContentById(@Param("id")String id);
}