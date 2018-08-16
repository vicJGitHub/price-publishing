package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.PriceCollection;
import java.math.BigDecimal;

public class PriceCollectionRep {

    private String productId;
    private String productName;
    private String unit;
    private BigDecimal price;
    private String priceCollectId;

    public PriceCollectionRep() {
    }

    public PriceCollectionRep(PriceCollection priceCollection, String productName, String unit) {
        this.setPrice(priceCollection.getPrice());
        this.setProductName(productName);
        this.setProductId(priceCollection.getProductId());
        this.setUnit(unit);
        this.setPriceCollectId(priceCollection.getId());
    }

    private void setPriceCollectId(String id) {
        this.priceCollectId = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceCollectId() {
        return priceCollectId;
    }
}
