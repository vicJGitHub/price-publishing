package com.hywa.pricepublish.service.login.impl;

import com.hywa.pricepublish.dao.entity.User;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.service.login.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    UserService userService;


    @Test
    //直接使用redisTemplate存取字符串
    public void setAndGet() {
        redisTemplate.opsForValue().set("test:set", "testValue1");
        Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
    }

    @Test
    //直接使用redisTemplate存取对象
    public void setAndGetAUser() {
        UserRep userRep = new UserRep();
        userRep.setName("陈俊方");
        userRep.setPwd("jiangzhouzhou");
        redisTemplate.opsForValue().set("test:setUser", userRep);
        Assert.assertEquals(userRep.getName(), ((UserRep) redisTemplate.opsForValue().get("test:setUser")).getName());
    }

    @Test
    public void save() {

    }

    @Test
    public void findByName() {
        User junFang = userService.findByName("jiangzhouzhou");
        System.out.println(junFang);
    }
}