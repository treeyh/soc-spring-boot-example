package com.treeyh.example.springboot.web.unittest;

import com.treeyh.common.model.result.CallResult;
import com.treeyh.common.utils.JsonUtils;
import com.treeyh.example.springboot.api.enums.StatusEnum;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import com.treeyh.example.springboot.service.UserInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
 * @create 2019-01-17 8:26 PM
 */
public class UserInfoControllerTestByMockService extends BaseTest {


    @SpyBean
    private UserInfoService userInfoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test01Query() throws Exception {

        UserInfoBo userInfoBo = new UserInfoBo();
        userInfoBo.setId(100L);
        userInfoBo.setName("name100");
        userInfoBo.setUpdateTime(new Date());
        userInfoBo.setSex((byte)1);
        userInfoBo.setWeight(1.1D);
        userInfoBo.setStatus(StatusEnum.ACTIVE.getCode());
        userInfoBo.setCreateTime(new Date());
        BDDMockito.given(this.userInfoService.queryById(userInfoBo.getId())).willReturn(CallResult.makeCallResult(true, SysResultCode.SUCCESS, userInfoBo, null));


        CallResult<UserInfoBo> demoModelCallResult = this.userInfoService.queryById(100L);

        System.out.println(JsonUtils.toJson(demoModelCallResult));

        Assert.assertTrue(demoModelCallResult.isSuccess());
        Assert.assertTrue(demoModelCallResult.getResult().getName().equals("name100"));

        demoModelCallResult = this.userInfoService.queryById(1L);

        System.out.println(JsonUtils.toJson(demoModelCallResult));
        Assert.assertTrue(demoModelCallResult.isSuccess());
    }


    @Test
    public void test02Query() throws Exception {

        UserInfoBo userInfoBo = new UserInfoBo();
        userInfoBo.setId(100L);
        userInfoBo.setName("name100");
        userInfoBo.setUpdateTime(new Date());
        userInfoBo.setSex((byte)1);
        userInfoBo.setWeight(1.1D);
        userInfoBo.setStatus(StatusEnum.ACTIVE.getCode());
        userInfoBo.setCreateTime(new Date());

        BDDMockito.given(this.userInfoService.queryById(userInfoBo.getId())).willReturn(CallResult.makeCallResult(true, SysResultCode.SUCCESS, userInfoBo, null));


        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("name1"))
                .andReturn().getResponse().getContentAsString();


        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andReturn().getResponse().getContentAsString();
    }

}
