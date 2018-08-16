package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.MarketRecentUseReps;
import com.hywa.pricepublish.representation.MarketRep;
import com.hywa.pricepublish.representation.MarketReps;
import com.hywa.pricepublish.representation.MarketsReps;

public interface MarketService {

    MarketsReps findMarkets(String marketTypeCode, String marketName, String region, Integer pageNum,
                            Integer pageSize);

    void save(MarketRep marketRep, String userId);

    MarketRecentUseReps findMarketRecentUse(String userId);

    void update(MarketRep marketRep, String userId);

    void deleteById(String marketId);
}
