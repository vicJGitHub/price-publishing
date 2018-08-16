package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Market;
import com.hywa.pricepublish.dao.entity.MarketExample;
import java.util.List;
import java.util.Map;

import com.hywa.pricepublish.dao.entity.entityExt.MarketExt;
import org.apache.ibatis.annotations.Param;

public interface MarketMapper {
    int countByExample(MarketExample example);

    int deleteByExample(MarketExample example);

    int deleteByPrimaryKey(String id);

    int insert(Market record);

    int insertSelective(Market record);

    List<Market> selectByExample(MarketExample example);

    Market selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Market record, @Param("example") MarketExample example);

    int updateByExample(@Param("record") Market record, @Param("example") MarketExample example);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);

    List<Market> selectMarketsByTypeAndRegion(String marketTypeCode, String regionId);

    String selectNameById(String marketId);

    List<MarketExt> findMarkets(@Param("marketTypeId") String marketTypeId, @Param("marketName") String marketName, @Param("regionId") String regionId);
}