package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class PriceCollectionReps {

    private Long total;
    private List<PriceCollectionRep> list;

    public PriceCollectionReps() {
    }

    public PriceCollectionReps(List<PriceCollectionRep> priceCollectionReps) {
        this.setList(priceCollectionReps);
    }

    public PriceCollectionReps(Long total, List<PriceCollectionRep> priceCollectionReps) {
        this(priceCollectionReps);
        this.setTotal(total);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<PriceCollectionRep> getList() {
        return list;
    }

    public void setList(List<PriceCollectionRep> priceCollectionReps) {
        this.list = new ArrayList<>(priceCollectionReps);
    }
}
