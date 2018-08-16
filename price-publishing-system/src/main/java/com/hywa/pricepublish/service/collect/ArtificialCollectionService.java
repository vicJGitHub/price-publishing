package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.ArtificialCollectionReps;
import java.util.List;
import java.util.Map;

public interface ArtificialCollectionService {

    void delete(String id);

    ArtificialCollectionReps findList(int pageNum, int pageSize);

    void save(List<Map<String,String>> maps, String userId);
}
