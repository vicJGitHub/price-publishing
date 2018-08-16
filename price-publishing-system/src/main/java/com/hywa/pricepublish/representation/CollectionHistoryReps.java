package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;

public class CollectionHistoryReps {

    private long total;

    private List<CollectionHistoryRep> list;

    public CollectionHistoryReps() {
    }

    public CollectionHistoryReps(List<CollectionHistoryRep> list) {
        this.list = list;
    }

    public CollectionHistoryReps(List<CollectionHistoryRep> list, long total) {
        this(list);
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<CollectionHistoryRep> getList() {
        return list;
    }

    public void setList(List<CollectionHistoryRep> collectionHistoryReps) {
        this.list = new ArrayList<>(collectionHistoryReps);
    }
}
