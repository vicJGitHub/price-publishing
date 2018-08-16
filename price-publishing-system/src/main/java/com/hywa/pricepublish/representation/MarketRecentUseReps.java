package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class MarketRecentUseReps {

    List<MarketRecentUseRep> list;

    public MarketRecentUseReps(List<MarketRecentUseRep> list) {
        this.setList(list);
    }

    public void setList(List<MarketRecentUseRep> list) {
        this.list = new ArrayList<>(list);
    }

    public List<MarketRecentUseRep> getList() {
        return list;
    }
}
