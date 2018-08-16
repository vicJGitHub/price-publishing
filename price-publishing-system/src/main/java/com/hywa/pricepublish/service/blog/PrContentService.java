package com.hywa.pricepublish.service.blog;

import com.hywa.pricepublish.representation.PrContentInfoRep;
import com.hywa.pricepublish.representation.PrContentInfoReps;

public interface PrContentService {

    void save(PrContentInfoRep prContentInfoRep);

    void update(PrContentInfoRep prContentInfoRep);

    PrContentInfoReps findArticleInfoAll(int pageNum, int pageSize, PrContentInfoRep prContentInfoRep);

    PrContentInfoRep findContentById(String id);

}
