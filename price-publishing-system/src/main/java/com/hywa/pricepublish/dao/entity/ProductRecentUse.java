package com.hywa.pricepublish.dao.entity;

public class ProductRecentUse {
    private Integer id;

    private String userId;

    private String productId;

    public ProductRecentUse() {
    }

    public ProductRecentUse(String productId, String userId) {
        this.setUserId(userId);
        this.setProductId(productId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }
}