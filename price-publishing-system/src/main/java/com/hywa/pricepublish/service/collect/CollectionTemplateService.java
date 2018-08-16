package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.CollectionTemplateRep;
import com.hywa.pricepublish.representation.CollectionTemplateReps;

public interface CollectionTemplateService {

    CollectionTemplateReps findByUserId(String userId, Integer pageNum, Integer pageSize);

    void save(CollectionTemplateRep templateRep, String userId);

    CollectionTemplateRep findByTemplateId(String templateId);

    void update(CollectionTemplateRep templateRep);

    void delete(String templateId);
}
