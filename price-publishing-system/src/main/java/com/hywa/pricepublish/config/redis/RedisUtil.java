package com.hywa.pricepublish.config.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存
     */
    public void set(String key, String value) {
        System.out.println(value);
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.debug("RedisUtil:set cache key={},value={}", key, value);
    }

    /**
     * 设置缓存对象
     */
    public <T> void setObject(String key, T obj, int expireTime) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        System.out.println(JSON.toJSONString(obj));
        operations.set(key, JSON.toJSONString(obj), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取指定key的缓存
     */
    public String getObject(String key) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key).toString();
    }

    /**
     * 判断当前key值 是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 设置缓存，并且自己指定过期时间
     */
    public void setWithExpireTime(String key, String value, int expireTime) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, TimeUnit.SECONDS);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, expireTime);
    }

    /**
     * 获取指定key的缓存
     */
    public String get(String key) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object value = operations.get(key);
        if (value == null) {
            return null;
        }
        LOGGER.debug("RedisUtil:getChildren cache key={},value={}", key, value);
        return value.toString();
    }

    /**
     * 删除指定key的缓存
     */
    public void delete(String key) {
        redisTemplate.delete(key);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }

    /**
     * 设置过期时间
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
}









