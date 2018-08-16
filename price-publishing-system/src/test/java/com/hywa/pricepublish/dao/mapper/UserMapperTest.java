package com.hywa.pricepublish.dao.mapper;

import com.alibaba.fastjson.JSON;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    //预设值
    private User user;

    //预期值
    private User resultUser;

    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() {
        initData();
    }

    public void initData() {
        //参数
        user = new User();//初始化数据
        user.setId(UUIDUtils.randomUUID());
        user.setName("test");
        user.setAge(0);
        user.setSex((short) 1);
        user.setTelephone("18610093405");
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setJob("test_系统架构");
        user.setWorkUnit("test_贵阳慧云网安科技有限公司");

        //结果值
        resultUser=new User();
        resultUser.setId("9960267a094e484fa77601dd66c5f2b9");
        resultUser.setAge(18);
        resultUser.setName("jzz");
        resultUser.setIsDel((short)0);
        resultUser.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        resultUser.setWorkUnit("慧云网安");
    }


    @Test
    @Transactional
    @Rollback
    //添加事务,方法结束后自动回滚数据防止脏数据
    public void insert() throws Exception {
        assertEquals(1, userMapper.insert(user));
    }


    @Test
    public void selectByUserName()throws Exception  {
        assertEquals(JSON.toJSONString(resultUser).trim(),JSON.toJSONString(userMapper.selectByUserName("jzz")).trim());
    }


}