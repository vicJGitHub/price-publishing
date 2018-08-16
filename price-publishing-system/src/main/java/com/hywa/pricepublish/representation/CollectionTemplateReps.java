package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class CollectionTemplateReps {

    private List<CollectionTemplateRep> list;

    private Long total;

    public CollectionTemplateReps() {
    }

    public CollectionTemplateReps(List<CollectionTemplateRep> list) {
        this.setList(list);
    }

    public CollectionTemplateReps(List<CollectionTemplateRep> list, Long total) {
        this(list);
        this.setTotal(total);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<CollectionTemplateRep> getList() {
        return list;
    }

    public void setList(List<CollectionTemplateRep> collectionTemplateReps) {
        this.list = new ArrayList<>(collectionTemplateReps);
    }

}
