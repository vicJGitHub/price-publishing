package com.hywa.pricepublish.dao.entity;

import java.util.Date;

public class CollectionHistory {

    private String id;

    private String priceCollectId;

    private String marketName;

    private Date collectionTime;

    private Short isDel;

    private String userId;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPriceCollectId() {
        return priceCollectId;
    }

    public void setPriceCollectId(String priceCollectId) {
        this.priceCollectId = priceCollectId == null ? null : priceCollectId.trim();
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public Date getCreateTime() {
        return createTime;
    }
}