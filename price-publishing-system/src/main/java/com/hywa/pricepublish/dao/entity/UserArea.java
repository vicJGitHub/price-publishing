package com.hywa.pricepublish.dao.entity;

public class UserArea extends UserAreaKey {
    private Short type;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public UserArea(){}

    public UserArea(String userId,String regionId,Short type){
        super.setUserId(userId);
        super.setRegionId(regionId);
        this.type=type;
    }
}