package com.hywa.pricepublish.controller.login;

import com.alibaba.fastjson.JSON;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.representation.UserReps;
import com.hywa.pricepublish.service.login.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserReps userReps;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        initData();
    }

    private void initData() {
        userReps = new UserReps();

        List<UserRep> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserRep userRep = new UserRep();
            userRep.setName("test" + i);
            userRep.setRegionId("regionId" + i);
            userRep.setWorkUnit("work" + i);
            userRep.setPhone("phone" + i);
            userRep.setUserId("id" + i);
            list.add(userRep);
        }
        userReps.setList(list);
    }

    @Test
    public void findAllUser() {
        Mockito.when(userService.findUsers(1, 10, null,null, "贵阳慧云网安科技有限公司"))
                .thenReturn(userReps);

        ResponseBase<UserReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(userReps);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/findAll")
                    .param("workUnit", "贵阳慧云网安科技有限公司"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(result ->
                            Assertions.assertThat(
                                    JSON.parseObject(result.getResponse().getContentAsString())
                                            .toJSONString())
                                    .isEqualTo(JSON.toJSON(responseBase).toString())
                    ).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mockito.verify(userService).findUsers(1, 10, null,null, "贵阳慧云网安科技有限公司");
    }


    @Test
    public void login() {
    }

    @Test
    public void registerUser() {
    }

    @Test
    public void updateUserRole() {
    }

    @Test
    public void deleteUser() {
    }

}