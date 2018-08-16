package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.MarketRecentUse;
import com.hywa.pricepublish.dao.entity.MarketRecentUseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketRecentUseMapper {
    int countByExample(MarketRecentUseExample example);

    int deleteByExample(MarketRecentUseExample example);

    int deleteByPrimaryKey(String id);

    int insert(MarketRecentUse record);

    int insertSelective(MarketRecentUse record);

    List<MarketRecentUse> selectByExample(MarketRecentUseExample example);

    MarketRecentUse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MarketRecentUse record, @Param("example") MarketRecentUseExample example);

    int updateByExample(@Param("record") MarketRecentUse record, @Param("example") MarketRecentUseExample example);

    int updateByPrimaryKeySelective(MarketRecentUse record);

    int updateByPrimaryKey(MarketRecentUse record);
}