package com.hywa.pricepublish.common.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        return replace(uuid);
    }

    public static String replace(String uuid) {
        return uuid.replaceAll("-", "");
    }
}
