package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductTypeReps {

    private List<ProductTypeRep> list;

    public ProductTypeReps(List<ProductTypeRep> productTypeRepList) {
        this.setList(productTypeRepList);
    }

    public List<ProductTypeRep> getList() {
        return list;
    }

    public void setList(List<ProductTypeRep> list) {
        this.list = new ArrayList<>(list);
    }
}
