package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DictReps {

    long total;
    List<DictRep> list;

    public DictReps(long total, List<DictRep> dictList) {
        this.setTotal(total);
        this.setList(dictList);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<DictRep> getList() {
        return list;
    }

    public void setList(List<DictRep> dictRepList) {
        list = new ArrayList<>(dictRepList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DictReps dictReps = (DictReps) o;
        return Objects.equals(list, dictReps.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "DictReps{" +
                "list=" + list +
                '}';
    }
}
