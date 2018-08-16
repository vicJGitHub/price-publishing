package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class ProductReps {

    private Long total;
    private List<ProductRep> list;

    public ProductReps() {
    }

    public ProductReps(List<ProductRep> list) {
        this.list = list;
    }

    public ProductReps(List<ProductRep> list, Long total) {
        this.setTotal(total);
        this.setList(list);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<ProductRep> getList() {
        return list;
    }

    public void setList(List<ProductRep> productReps) {
        this.list = new ArrayList<>(productReps);
    }
}
