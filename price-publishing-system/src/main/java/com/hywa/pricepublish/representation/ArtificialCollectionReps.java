package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArtificialCollectionReps {

    long total;
    List<ArtificialCollectionRep> list;

    public ArtificialCollectionReps(long total,
            List<ArtificialCollectionRep> list) {
        this.setList(list);
        this.setTotal(total);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ArtificialCollectionRep> getList() {
        return list;
    }

    public void setList(List<ArtificialCollectionRep> list) {
        this.list = new ArrayList<>(list);
    }
}
