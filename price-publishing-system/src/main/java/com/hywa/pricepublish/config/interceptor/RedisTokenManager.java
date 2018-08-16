package com.hywa.pricepublish.config.interceptor;

import static com.hywa.pricepublish.common.enums.CommonEnum.RE_LOGOUT;

import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private StringRedisTemplate redis;

    @Override
    public TokenModel createToken(String userId) {
        String token = UUIDUtils.randomUUID();
        TokenModel model = new TokenModel(userId, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId)
                .set(token, ConstantPool.TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        log.info("tokenModel : " + model.toString());
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(ConstantPool.TOKEN_EXPIRES_SECOND, TimeUnit.HOURS);
        return true;
    }


    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(String userId) {
        //应该对是否退出成功做出相应的判断
        if (!redis.delete( userId)) {
            throw new GlobalException(RE_LOGOUT.getIndex(), RE_LOGOUT.getValue());
        }
    }
}
