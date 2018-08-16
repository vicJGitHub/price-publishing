package com.hywa.pricepublish.service.collect.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.utils.DateUtils;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.PriceCrawlerData;
import com.hywa.pricepublish.dao.entity.MarketRecentUse;
import com.hywa.pricepublish.dao.entity.PriceCollection;
import com.hywa.pricepublish.dao.entity.PriceCollectionExample;
import com.hywa.pricepublish.dao.entity.Product;
import com.hywa.pricepublish.dao.entity.ProductRecentUse;
import com.hywa.pricepublish.dao.mapper.PriceCrawlerDataMapper;
import com.hywa.pricepublish.dao.mapper.MarketRecentUseMapper;
import com.hywa.pricepublish.dao.mapper.PriceCollectionMapper;
import com.hywa.pricepublish.dao.mapper.ProductMapper;
import com.hywa.pricepublish.dao.mapper.ProductRecentUseMapper;
import com.hywa.pricepublish.event.PriceCollectHistoryCreateEvent;
import com.hywa.pricepublish.representation.PriceCollectStatisticsRep;
import com.hywa.pricepublish.representation.PriceCollectStatisticsReps;
import com.hywa.pricepublish.representation.PriceCollectionRep;
import com.hywa.pricepublish.representation.PriceCollectionReps;
import com.hywa.pricepublish.representation.PriceCrawlerDataRep;
import com.hywa.pricepublish.representation.PriceCrawlerDataReps;
import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.service.collect.PriceCollectionService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PriceCollectionServiceImpl implements PriceCollectionService {

    @Autowired
    private PriceCollectionMapper priceCollectionMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRecentUseMapper productRecentUseMapper;

    @Autowired
    private MarketRecentUseMapper marketRecentUseMapper;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PriceCrawlerDataMapper priceCrawlerDataMapper;

    @Override
    @Transactional
    public void save(String userId, String dateTime, String marketId, String marketName,
            List<ProductRep> reps) {
        String historyId = UUIDUtils.randomUUID();
        List<PriceCollection> priceCollections = new ArrayList<>();
        List<ProductRecentUse> productRecentUses = new ArrayList<>();
        for (ProductRep productRep : reps) {
            PriceCollection priceCollection = new PriceCollection();
            priceCollection.setCreateUser(userId);
            priceCollection.setId(UUIDUtils.randomUUID());
            priceCollection.setMarketId(marketId);
            priceCollection.setProductId(productRep.getProductId());
            priceCollection.setPrice(productRep.getPrice());
            priceCollection.setHistoryId(historyId);
            priceCollection.setUnit(productRep.getUnit());
            try {
                priceCollection
                        .setCreateTime(DateUtils.stringToDate(dateTime, DateUtils.DEFAULT_FORMAT));
                priceCollection
                        .setUpdateTime(DateUtils.stringToDate(dateTime, DateUtils.DEFAULT_FORMAT));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            priceCollections.add(priceCollection);
            productRecentUses.add(new ProductRecentUse(productRep.getProductId(), userId));
        }

        priceCollectionMapper.insertBatch(priceCollections);
        try {
            saveCollectHistory(dateTime, marketName, historyId, userId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        collectProductRecentUse(productRecentUses);
        collectMarketRecentUse(userId, marketId);
    }

    private void collectMarketRecentUse(String userId, String marketId) {
        MarketRecentUse recentUse = new MarketRecentUse();
        recentUse.setId(UUIDUtils.randomUUID());
        recentUse.setMarketId(marketId);
        recentUse.setUserId(userId);
        marketRecentUseMapper.insert(recentUse);
    }

    private void collectProductRecentUse(List<ProductRecentUse> productRecentUses) {
        productRecentUseMapper.insertBatch(productRecentUses);
    }

    private void saveCollectHistory(String dateTime, String marketName, String historyId,
            String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("historyId", historyId);
        map.put("marketName", marketName);
        map.put("dateTime", dateTime);
        map.put("userId", userId);
        context.publishEvent(new PriceCollectHistoryCreateEvent(map));
    }

    @Override
    public PriceCollectionReps findCollect(String collectHistoryId) {
        PriceCollectionExample example = new PriceCollectionExample();
        example.createCriteria().andHistoryIdIsEqualsTo(collectHistoryId);
        List<PriceCollection> priceCollections = priceCollectionMapper.selectByExample(example);

        List<PriceCollectionRep> priceCollectionReps = convertProductList(
                priceCollections);
        return new PriceCollectionReps(priceCollectionReps);
    }

    @Override
    @Transactional
    public void updateCollects(String historyId, PriceCollectionReps priceCollectionReps) {
        List<String> collectIds = priceCollectionMapper.findCollectIds(historyId);

        for (PriceCollectionRep priceCollectionRep : priceCollectionReps.getList()) {
            if (StringUtils.isEmpty(priceCollectionRep.getPriceCollectId())) {
                savePriceCollect(priceCollectionRep, historyId);
            } else {
                collectIds.remove(priceCollectionRep.getPriceCollectId());
                updateCollect(priceCollectionRep);
            }
        }

        for (String collectId : collectIds) {
            priceCollectionMapper.deleteById(collectId);
        }
    }

    private void savePriceCollect(PriceCollectionRep priceCollectionRep, String historyId) {
        PriceCollection priceCollect = new PriceCollection();
        priceCollect.setUnit(priceCollectionRep.getUnit());
        priceCollect.setProductId(priceCollectionRep.getProductId());
        priceCollect.setId(UUIDUtils.randomUUID());
        priceCollect.setPrice(priceCollectionRep.getPrice());
        priceCollect.setCreateTime(new Date());
        priceCollect.setUpdateTime(new Date());
        priceCollect.setHistoryId(historyId);
        priceCollectionMapper.insert(priceCollect);
    }

    public void updateCollect(PriceCollectionRep priceCollectionRep) {
        PriceCollection priceCollection = new PriceCollection();
        priceCollection.setPrice(priceCollectionRep.getPrice());

        PriceCollectionExample example = new PriceCollectionExample();
        example.createCriteria().andIdEqualTo(priceCollectionRep.getPriceCollectId());
        priceCollectionMapper.updateByExample(priceCollection, example);
    }

    @Override
    public PriceCollectStatisticsReps findCollect(
            int pageNum, int pageSize, String productNameOrMarketName,
            String priceType, String startTime, String endTime) {

        PageHelper.startPage(pageNum, pageSize, true);
        List<PriceCollectStatisticsRep> priceCollections = priceCollectionMapper
                .selectCollectMultiCondition(
                        productNameOrMarketName, priceType, startTime, endTime);
        PageInfo<PriceCollectStatisticsRep> page = new PageInfo<>(priceCollections);

        return new PriceCollectStatisticsReps(page.getTotal(), priceCollections);
    }

    private List<PriceCollectionRep> convertProductList(List<PriceCollection> priceCollections) {
        List<PriceCollectionRep> priceCollectionReps = new ArrayList<>();
        for (PriceCollection priceCollection : priceCollections) {
            Product product = productMapper.selectNameAndUnitById(priceCollection.getProductId());
            if (product == null) {
                continue;
            }
            PriceCollectionRep priceCollectionRep = new PriceCollectionRep(
                    priceCollection, product.getName(), product.getUnit());
            priceCollectionReps.add(priceCollectionRep);
        }
        return priceCollectionReps;
    }

    @Override
    public PriceCrawlerDataReps findCrawlerCollectData(int pageNum, int pageSize,
            String productNameOrMarketName, String priceType, String startTime, String endTime) {

        PageHelper.startPage(pageNum, pageSize, true);
        List<PriceCrawlerData> priceCrawlerDataList = priceCrawlerDataMapper
                .selectByMultiCondition(productNameOrMarketName, priceType, startTime, endTime);
        PageInfo<PriceCrawlerData> page = new PageInfo<>(priceCrawlerDataList);

        List<PriceCrawlerDataRep> priceCrawlerDataReps = new ArrayList<>();
        for (PriceCrawlerData priceCrawlerData : priceCrawlerDataList) {
            PriceCrawlerDataRep priceCrawlerDataRep = new PriceCrawlerDataRep(priceCrawlerData);
            priceCrawlerDataReps.add(priceCrawlerDataRep);
        }

        return new PriceCrawlerDataReps(page.getTotal(), priceCrawlerDataReps);
    }
}
