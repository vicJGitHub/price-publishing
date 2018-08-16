package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class MarketReps {

    private long total;
    private List<MarketRep> list;

    public MarketReps() {
    }

    public MarketReps(List<MarketRep> list) {
        this.setList(list);
    }

    public MarketReps(List<MarketRep> list, long total) {
        this(list);
        this.total = total;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<MarketRep> getList() {
        return list;
    }

    public void setList(List<MarketRep> marketReps) {
        this.list = new ArrayList<>(marketReps);
    }
}
