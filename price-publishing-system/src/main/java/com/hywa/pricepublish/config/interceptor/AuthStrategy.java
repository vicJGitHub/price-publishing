package com.hywa.pricepublish.config.interceptor;

import com.hywa.pricepublish.common.enums.Module;

/**
 * 策略接口，所有策略都应该实现的接口
 */
public interface AuthStrategy {

    boolean executeAuth(Module[] modules, String userId) throws Exception;
}
