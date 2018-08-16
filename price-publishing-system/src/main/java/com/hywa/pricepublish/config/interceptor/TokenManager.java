package com.hywa.pricepublish.config.interceptor;

public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     */
    TokenModel createToken(String userId);

    /**
     * 检查token是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     */
    TokenModel getToken(String authentication);

    /**
     * 清除token
     */
    void deleteToken(String userId);
}
