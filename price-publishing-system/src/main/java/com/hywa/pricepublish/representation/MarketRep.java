package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.Market;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketRep {

    private String marketId;

    @NotNull(message = "市场名称不能为空")
    private String marketName;
    private String marketType;
    private String regionId;
    private Short regionType;
    private String priceType;
    private String province;
    private String city;
//    @NotNull(message = "市场绑定区域的最小单元为区、县")
    private String county;
    private String town;
    private String village;

    public MarketRep(Market market) {
        this.setMarketName(market.getName());
        this.setMarketType(market.getMarketType());
        this.setMarketId(market.getId());
    }
}
