package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.CollectionHistoryReps;

public interface CollectionHistoryService {

    CollectionHistoryReps collectHistory(String userId, Integer pageNum, Integer pageSize);

    void save(String marketName, String historyId, String dateTime, String userId);
}
