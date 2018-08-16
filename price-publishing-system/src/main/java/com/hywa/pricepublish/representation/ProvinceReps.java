package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.Province;

import java.util.ArrayList;
import java.util.List;

public class ProvinceReps {

    private long total;
    private List<Province> list;

    public ProvinceReps() {
    }

    public ProvinceReps(long total, List<Province> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Province> getList() {
        return list;
    }

    public void setList(List<Province> list) {
        this.list = list;
    }
}
