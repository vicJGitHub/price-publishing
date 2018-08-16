package com.hywa.pricepublish.representation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketRecentUseRep {

    private String marketId;
    private String marketName;

    public MarketRecentUseRep(String marketId, String marketName) {
        this.setMarketId(marketId);
        this.setMarketName(marketName);
    }
}
