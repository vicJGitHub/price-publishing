package com.hywa.pricepublish.config.interceptor;

import lombok.Data;

@Data
public class TokenModel {

    private String userId;

    private String token;

    public TokenModel(String userId, String token) {
        this.setUserId(userId);
        this.setToken(token);
    }
}
