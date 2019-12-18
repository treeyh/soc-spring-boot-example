package com.treeyh.example.springboot.web.unittest;

import com.treeyh.common.utils.JsonUtils;
import com.treeyh.common.utils.UuidUtils;
import com.treeyh.example.springboot.api.enums.DeleteEnum;
import com.treeyh.example.springboot.api.enums.SexEnum;
import com.treeyh.example.springboot.api.model.base.req.UserInfoReq;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

/**
 * @author TreeYH
 * @version 1.0
 * @description
 * @create 2019-01-17 8:25 PM
 */
public class UserInfoControllerTestByMockMvc extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testQuery() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/2").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }



    @Test
    public void testQueryByPage() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user").contentType(MediaType.APPLICATION_JSON_UTF8).param("page", "1").param("size", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user").contentType(MediaType.APPLICATION_JSON_UTF8).param("name", "name").param("page", "1").param("size", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }


    @Test
    public void testAdd() throws Exception {
        Long time = System.currentTimeMillis();

        UserInfoReq userInfoReq = new UserInfoReq();
//        userInfoReq.setName("name"+ System.currentTimeMillis());
        userInfoReq.setName("name");
        userInfoReq.setBirthday(new Date(System.currentTimeMillis()));
        userInfoReq.setId(UuidUtils.getNewIdByLong());
        userInfoReq.setRemark("remark");
        userInfoReq.setSex(SexEnum.MAN.getCode());
        userInfoReq.setWeight(79.2D);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(userInfoReq)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testAddTransactional() throws Exception {
        Long time = System.currentTimeMillis();

        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setName("test");
        userInfoReq.setBirthday(new Date(System.currentTimeMillis()));
        userInfoReq.setId(UuidUtils.getNewIdByLong());
        userInfoReq.setRemark("remark");
        userInfoReq.setSex(SexEnum.MAN.getCode());
        userInfoReq.setWeight(79.2D);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(userInfoReq)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }
}
