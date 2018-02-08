package com.chenyu.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author yu_chen
 * @create 2018-02-08 16:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    /**
     * 在单元测试运行之前模拟一个mvc的执行环境
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQueryUserSuccess() throws Exception {
        String result = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username", "tom")
                .param("age", "10")
                .param("ageTo", "20")
                .param("xxx", "xxx"))
                //期望状态是200
                .andExpect(status().isOk())
                //期望返回的是一个集合，并且集合的长度是3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenQueryUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("tom"))
                //将返回值作为字符串输出
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void whenQueryUserInfoFail() throws Exception {
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //期望客户端状态码是4xx
                .andExpect(status().is4xxClientError());

    }


    @Test
    public void whenCreateUserSuccess() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":null}";
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    public void whenUpdateUserSuccess() throws Exception {
        Date date = new Date();
        String content = "{\"userName\":\"tom\",\"password\":null,\"birthDay\":" + date.getTime() + "}";
        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }
}
