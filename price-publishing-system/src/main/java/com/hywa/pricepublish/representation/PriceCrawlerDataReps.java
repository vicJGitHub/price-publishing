package com.hywa.pricepublish.representation;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriceCrawlerDataReps {

    Long total;
    List<PriceCrawlerDataRep> list;

    public PriceCrawlerDataReps(Long total,
            List<PriceCrawlerDataRep> list) {
        this.total = total;
        this.list = list;
    }

    public List<PriceCrawlerDataRep> getList() {
        return list;
    }

    public void setList(List<PriceCrawlerDataRep> list) {
        this.list = new ArrayList(list);
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
