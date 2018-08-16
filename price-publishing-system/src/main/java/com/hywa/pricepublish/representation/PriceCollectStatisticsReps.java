package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class PriceCollectStatisticsReps {

    Long total;
    List<PriceCollectStatisticsRep> list;

    public PriceCollectStatisticsReps() {
    }

    public PriceCollectStatisticsReps(Long total,
            List<PriceCollectStatisticsRep> list) {
        this.setTotal(total);
        this.setList(list);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<PriceCollectStatisticsRep> getList() {
        return list;
    }

    public void setList(
            List<PriceCollectStatisticsRep> list) {
        this.list = new ArrayList<>(list);
    }
}
