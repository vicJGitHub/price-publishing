package com.hywa.pricepublish.event;

import org.springframework.context.ApplicationEvent;

public class ProductDeletedEvent extends ApplicationEvent {

    public ProductDeletedEvent(Object source) {
        super(source);
    }
}
