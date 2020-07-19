package org.artc.web;

import org.artc.security.web.LoginController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        System.out.println("开始测试...");
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void test() throws Exception {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("loginName", "admin");
        param.add("password", "963963");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .params(param)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @After
    public void after() {
        System.out.println("测试结束...");
    }

}
