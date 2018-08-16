package com.hywa.pricepublish.service.inform;

import com.hywa.pricepublish.representation.PrInformRep;
import com.hywa.pricepublish.representation.PrInformReps;

public interface PrInformEventService {

    PrInformRep save(PrInformRep prInformRep);

    void update(PrInformRep prInformRep);

    Long codeIsEmpty();

    PrInformReps findAll(int pageNum,int pageSize, PrInformRep prInformRep);

    PrInformRep findById(String id);

}
