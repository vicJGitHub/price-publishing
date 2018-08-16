package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MenuReps {

    private List<MenuRep> list;

    public MenuReps(List<MenuRep> list) {
        this.setList(list);
    }

    public List<MenuRep> getList() {
        return list;
    }

    public void setList(List<MenuRep> list) {
        this.list = new ArrayList<>(list);
    }
}
