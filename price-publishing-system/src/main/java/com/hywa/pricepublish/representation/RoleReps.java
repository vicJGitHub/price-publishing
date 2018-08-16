package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoleReps {

    private long total;

    private List<RoleRep> list;

    public RoleReps(List<RoleRep> list) {
        this.setList(list);
    }

    public RoleReps(List<RoleRep> list, long total) {
        this(list);
        this.setTotal(total);
    }

    public List<RoleRep> getList() {
        return list;
    }

    public void setList(List<RoleRep> list) {
        this.list = new ArrayList<>(list);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
