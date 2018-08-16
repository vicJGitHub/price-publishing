package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.City;

import java.util.List;

public class CityReps {

    private long total;
    private List<City> list;

    public CityReps() {
    }

    public CityReps(long total, List<City> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }
}
