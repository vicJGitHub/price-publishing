package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PriceCrawlerData;
import com.hywa.pricepublish.dao.entity.PriceCrawlerDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceCrawlerDataMapper {

    int countByExample(PriceCrawlerDataExample example);

    int deleteByExample(PriceCrawlerDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PriceCrawlerData record);

    int insertSelective(PriceCrawlerData record);

    List<PriceCrawlerData> selectByExample(PriceCrawlerDataExample example);

    PriceCrawlerData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PriceCrawlerData record,
            @Param("example") PriceCrawlerDataExample example);

    int updateByExample(@Param("record") PriceCrawlerData record,
            @Param("example") PriceCrawlerDataExample example);

    int updateByPrimaryKeySelective(PriceCrawlerData record);

    int updateByPrimaryKey(PriceCrawlerData record);

    List<PriceCrawlerData> selectByMultiCondition(
            @Param("productNameOrMarketName") String productNameOrMarketName,
            @Param("priceType") String priceType,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}