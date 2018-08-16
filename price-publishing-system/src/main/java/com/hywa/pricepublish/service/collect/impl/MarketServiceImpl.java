package com.hywa.pricepublish.service.collect.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Market;
import com.hywa.pricepublish.dao.entity.MarketRecentUse;
import com.hywa.pricepublish.dao.entity.MarketRecentUseExample;
import com.hywa.pricepublish.dao.entity.entityExt.MarketExt;
import com.hywa.pricepublish.dao.mapper.MarketMapper;
import com.hywa.pricepublish.dao.mapper.MarketRecentUseMapper;
import com.hywa.pricepublish.representation.*;
import com.hywa.pricepublish.service.collect.MarketService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private MarketRecentUseMapper marketRecentUseMapper;

    @Override
    public MarketsReps findMarkets(String marketTypeId, String marketName, String regionId,
                                   Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<MarketExt> markets = marketMapper.findMarkets(marketTypeId, marketName, regionId);
        return new MarketsReps(new PageInfo<>(markets).getTotal(),markets);
    }

    @Override
    public void save(MarketRep marketRep, String userId) {
        Market market = new Market();
        market.setCreateTime(new Date());
        market.setIsDel(ConstantPool.NOT_DEL);
        market.setCreateUser(userId);
        market.setName(marketRep.getMarketName());
        market.setUpdateTime(new Date());
        market.setId(UUIDUtils.randomUUID());
        market.setMarketType(marketRep.getMarketType());
        market.setPriceType(marketRep.getPriceType());
        market.setProvinceId(marketRep.getProvince());
        market.setCityId(marketRep.getCity());
        market.setCountyId(marketRep.getCounty());
        market.setTownId(marketRep.getTown());
        market.setVillageId(marketRep.getVillage());
        setRegion(marketRep, market);
        marketMapper.insert(market);
    }

    @Override
    @Transactional
    public void update(MarketRep marketRep, String userId) {
        Market market = new Market();
        market.setId(marketRep.getMarketId());
        market.setUpdateUser(userId);
        market.setName(marketRep.getMarketName());
        market.setUpdateTime(new Date());;
        market.setMarketType(marketRep.getMarketType());
        market.setPriceType(marketRep.getPriceType());
        market.setProvinceId(marketRep.getProvince());
        market.setCityId(marketRep.getCity());
        market.setCountyId(marketRep.getCounty());
        market.setTownId(marketRep.getTown());
        market.setVillageId(marketRep.getVillage());
        setRegion(marketRep, market);
        marketMapper.updateByPrimaryKey(market);
    }

    private void setRegion(MarketRep marketRep, Market market) {
        if (!StringUtils.isEmpty(marketRep.getProvince())) {
            String region = marketRep.getProvince();
            short type = 1;
            if (!StringUtils.isEmpty(marketRep.getCity())) {
                if (!StringUtils.isEmpty(marketRep.getCounty())) {
                    region = marketRep.getCounty();
                    type = 3;
                } else {
                    type = 2;
                    region = marketRep.getCity();
                }
            }
            market.setRegionId(region);
            market.setRegionType(type);
        }
    }

    @Override
    public MarketRecentUseReps findMarketRecentUse(String userId) {
        List<MarketRecentUseRep> marketRecentUseReps = new ArrayList<>();
        MarketRecentUseExample example = new MarketRecentUseExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<MarketRecentUse> marketRecentUses = marketRecentUseMapper.selectByExample(example);
        marketRecentUses.forEach(marketRecentUse -> {
            String marketId = marketRecentUse.getMarketId();
            String marketName = marketMapper.selectNameById(marketId);
            marketRecentUseReps.add(new MarketRecentUseRep(marketId, marketName));
        });
        return new MarketRecentUseReps(marketRecentUseReps);
    }

    @Override
    public void deleteById(String marketId) {
        marketMapper.deleteByPrimaryKey(marketId);
    }
}
