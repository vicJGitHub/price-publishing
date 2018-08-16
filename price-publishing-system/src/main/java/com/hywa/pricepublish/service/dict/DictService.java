package com.hywa.pricepublish.service.dict;

import com.hywa.pricepublish.representation.DictRep;
import com.hywa.pricepublish.representation.DictReps;

public interface DictService {

    DictReps findDictListByDictType(String dictType, int pageNum, int pageSize);

    DictReps findDictListByDictTypeId(String dictTypeId, int pageNum, int pageSize);

    void add(String userId, DictRep dictRep,Short level);

    void update(String userId, DictRep dictRep);

    void delete(String dictId);
}
