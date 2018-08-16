package com.hywa.pricepublish.config.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public List<Map<String, String>> getPermission(String userId) {
        //TODO redis中获取用户权限列表
//        String s = stringRedisTemplate.opsForValue().getChildren(userId + "permissions");
        List<Map<String, String>> maps = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("priName", "采集模块");
        maps.add(map);
        return maps;
    }
}
