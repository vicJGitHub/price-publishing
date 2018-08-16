package com.hywa.pricepublish.config.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.hywa.pricepublish.common.enums.Module;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface PermissionModule {

    /**
     * 执行方法所需要的权限，ALL表示所有权限都可以执行 belong的类型为数组，方便为接口添加多个模块，即一个接口可以属于多个模块 同时也可以反映出一个模块可以包含多个接口的关系
     */
    Module[] belong() default {Module.ALL};
}

