package com.hywa.pricepublish.event.eventhandler;

import com.hywa.pricepublish.dao.mapper.CollectionTemplateMapper;
import com.hywa.pricepublish.event.PriceCollectHistoryCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    @Autowired
    private CollectionTemplateMapper collectionTemplateMapper;

    @EventListener
    public void saveCollectionHistory(PriceCollectHistoryCreateEvent event) {
        String productId = (String) event.getSource();
        collectionTemplateMapper.deleteProduct(productId);
    }
}
