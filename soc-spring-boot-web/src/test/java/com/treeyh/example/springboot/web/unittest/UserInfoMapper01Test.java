package com.treeyh.example.springboot.web.unittest;

import com.treeyh.common.utils.JsonUtils;
import com.treeyh.common.utils.UuidUtils;
import com.treeyh.example.springboot.api.enums.DeleteEnum;
import com.treeyh.example.springboot.api.enums.SexEnum;
import com.treeyh.example.springboot.api.enums.StatusEnum;
import com.treeyh.example.springboot.dao.UserInfoPoMapper;
import com.treeyh.example.springboot.dao.po.UserInfoPo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author TreeYH
 * @version 1.0
 * @description
 * @create 2019-01-17 8:25 PM
 */
public class UserInfoMapper01Test extends BaseTest {

    @Autowired
    private UserInfoPoMapper userInfoPoMapper;

    @Test
    public void insert() throws Exception {
        int count = 0;
        Long time = System.currentTimeMillis();

        UserInfoPo userInfoPo = new UserInfoPo();
        userInfoPo.setName("name"+System.currentTimeMillis());
        userInfoPo.setDeleted(DeleteEnum.NO_DELETE.getCode());
        userInfoPo.setBirthday(new Date(System.currentTimeMillis()));
        userInfoPo.setCreateTime(new Date(System.currentTimeMillis()));
        userInfoPo.setId(UuidUtils.getNewIdByLong());
        userInfoPo.setRemark("remark");
        userInfoPo.setSex(SexEnum.MAN.getCode());
        userInfoPo.setStatus(StatusEnum.ACTIVE.getCode());
        userInfoPo.setUpdateTime(new Date(System.currentTimeMillis()));
        userInfoPo.setWeight(79.2D);

        count = userInfoPoMapper.insert(userInfoPo);
        assertEquals(1, count);
        System.out.printf("==========================================UserInfoMapper01Test.insert%s", JsonUtils.toJson(userInfoPo));
    }


    @Test
    public void queryById() throws Exception {
        Long id = 2L;
        UserInfoPo userInfoPo = userInfoPoMapper.selectById(id);
        Assertions.assertThat(userInfoPo.getId()).isEqualTo(id);
        System.out.printf("==========================================UserInfoMapper01Test.queryById%s", JsonUtils.toJson(userInfoPo));
    }
}
