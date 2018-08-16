package com.hywa.pricepublish.event;

import org.springframework.context.ApplicationEvent;

public class PriceCollectHistoryCreateEvent extends ApplicationEvent {

    public PriceCollectHistoryCreateEvent(Object source) {
        super(source);
    }
}
