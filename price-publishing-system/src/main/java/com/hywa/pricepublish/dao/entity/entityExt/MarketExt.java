package com.hywa.pricepublish.dao.entity.entityExt;

import com.hywa.pricepublish.dao.entity.Market;


public class MarketExt extends Market{
private String provinceName;
private String cityName;
private String countyName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}