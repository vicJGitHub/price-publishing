package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.entityExt.MarketExt;

import java.util.List;
import java.util.Map;

public class MarketsReps {

    private long total;
    private List<MarketExt> list;

    public MarketsReps(long total, List<MarketExt> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<MarketExt> getList() {
        return list;
    }

    public void setList(List<MarketExt> list) {
        this.list = list;
    }
}
