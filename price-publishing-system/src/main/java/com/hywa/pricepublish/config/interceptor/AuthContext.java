package com.hywa.pricepublish.config.interceptor;

import com.hywa.pricepublish.common.enums.Module;

/**
 * 权限策略上下文控制类
 */
public class AuthContext {

    private AuthStrategy authStrategy;

    public AuthContext(AuthStrategy strategy) {
        this.authStrategy = strategy;
    }

    /**
     * 执行策略
     */
    public boolean execute(Module[] modules, String userId) throws Exception {
        return this.authStrategy.executeAuth(modules, userId);
    }
}
