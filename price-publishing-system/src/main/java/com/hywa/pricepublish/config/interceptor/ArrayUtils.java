package com.hywa.pricepublish.config.interceptor;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static <T> boolean contains(T[] modules, T all) {
        List<T> list = Arrays.asList(modules);
        return list.contains(all);
    }
}
