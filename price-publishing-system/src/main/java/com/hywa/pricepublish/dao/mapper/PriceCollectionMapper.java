package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.PriceCollection;
import com.hywa.pricepublish.dao.entity.PriceCollectionExample;
import com.hywa.pricepublish.representation.PriceCollectStatisticsRep;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface PriceCollectionMapper {

    int countByExample(PriceCollectionExample example);

    int deleteByExample(PriceCollectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(PriceCollection record);

    int insertSelective(PriceCollection record);

    List<PriceCollection> selectByExample(PriceCollectionExample example);

    PriceCollection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PriceCollection record,
            @Param("example") PriceCollectionExample example);

    int updateByExample(@Param("record") PriceCollection record,
            @Param("example") PriceCollectionExample example);

    int updateByPrimaryKeySelective(PriceCollection record);

    int updateByPrimaryKey(PriceCollection record);

    void deleteById(String collectId);

    List<String> findCollectIds(String historyId);

    void insertBatch(List<PriceCollection> priceCollections);

    List<PriceCollectStatisticsRep> selectCollectMultiCondition(
            @Param("productNameOrMarketName") String productNameOrMarketName,
            @Param("priceType") String priceType,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}