package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.County;

import java.util.List;

public class CountyReps {

    private long total;
    private List<County> list;

    public CountyReps() {
    }

    public CountyReps(long total, List<County> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<County> getList() {
        return list;
    }

    public void setList(List<County> list) {
        this.list = list;
    }
}
