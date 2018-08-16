package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class UserReps {

    Long total;
    List<UserRep> list;

    public UserReps(Long total, List<UserRep> list) {
        this.setTotal(total);
        this.setList(list);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<UserRep> getList() {
        return list;
    }

    public void setList(List<UserRep> list) {
        this.list = new ArrayList<>(list);
    }
}
