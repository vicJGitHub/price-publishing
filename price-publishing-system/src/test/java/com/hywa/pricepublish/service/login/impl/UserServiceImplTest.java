package com.hywa.pricepublish.service.login.impl;

import com.alibaba.fastjson.JSON;
import com.hywa.pricepublish.dao.entity.User;
import com.hywa.pricepublish.dao.mapper.UserMapper;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.service.login.UserAreaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {


    //mock依赖层
    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAreaService userAreaService;

    //注入mock对象
    @InjectMocks
    private UserServiceImpl userService;

    //测试所用数据
    private User user;

    //预期值
    private UserRep userRep;

    @Before
    public void setUp() throws ParseException {
        initData();
    }

    //构建数据
    private void initData() throws ParseException {
        //参数
        user=new User();//初始化数据
        user.setId("b8d11ee289394be688ef3a4f6968efed");
        user.setName("admin");
        user.setAge(0);
        user.setSex((short) 1);
        user.setTelephone("18610093405");
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setJob("系统架构");
        user.setWorkUnit("贵阳慧云网安科技有限公司");
        //结果数据
        userRep = new UserRep(user.getId(), user.getName(), user.getTelephone(),
                user.getSex(), user.getJob(), user.getWorkUnit(), user.getAge(), "520101");

    }

    @Test
    public void userLogin() throws ParseException {

        //Mock依赖调用,返回指定数据
        Mockito.when(userMapper.selectByUserName("admin")).thenReturn(user);
        Mockito.when(userAreaService.findRegionId(user.getId())).thenReturn("520101");

        assertEquals(JSON.toJSONString(userService.userLogin("admin", "123456")).trim(), JSON.toJSONString(userRep).trim());
        //检验方法是否被调用,被调用几次
        Mockito.verify(userMapper, times(1)).selectByUserName("admin");
        Mockito.verify(userAreaService, times(1)).findRegionId(user.getId());

    }

    @Test
    public void save() {
        //public void save(UserRepExt userRep)
    }

}