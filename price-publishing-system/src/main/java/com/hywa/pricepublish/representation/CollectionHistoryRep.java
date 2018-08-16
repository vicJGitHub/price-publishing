package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.common.utils.DateUtils;
import com.hywa.pricepublish.dao.entity.CollectionHistory;
import java.util.Date;
import lombok.Data;

@Data
public class CollectionHistoryRep {

    private String collectTime;
    private String collectHistoryId;
    private String marketName;
    private boolean editable;

    public CollectionHistoryRep() {
    }

    public CollectionHistoryRep(CollectionHistory collectionHistory) {
        this.setCollectTime(DateUtils
                .formatDate(collectionHistory.getCollectionTime(), DateUtils.DEFAULT_FORMAT));
        this.setEditable(countInterval(collectionHistory.getCollectionTime()));
        this.setCollectHistoryId(collectionHistory.getId());
        this.setMarketName(collectionHistory.getMarketName());
    }

    private boolean countInterval(Date collectTime) {
        Date date = new Date();
        int i = (int) ((date.getTime() - collectTime.getTime()) / (1000 * 60 * 60 * 24));
        if (i > 7) {
            return false;
        }
        return true;
    }
}
