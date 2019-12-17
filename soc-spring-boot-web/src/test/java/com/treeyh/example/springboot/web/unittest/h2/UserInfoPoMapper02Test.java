package com.treeyh.example.springboot.web.unittest.h2;

import com.github.database.rider.core.api.dataset.DataSet;
import com.treeyh.common.utils.JsonUtils;
import com.treeyh.example.springboot.api.enums.DeleteEnum;
import com.treeyh.example.springboot.api.enums.SexEnum;
import com.treeyh.example.springboot.api.enums.StatusEnum;
import com.treeyh.example.springboot.dao.UserInfoPoMapper;
import com.treeyh.example.springboot.dao.po.UserInfoPo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author TreeYH
 * @version 1.0
 * @description
 * @create 2019-01-17 8:27 PM
 */
public class UserInfoPoMapper02Test extends BaseH2MockTest{

    @Autowired
    private UserInfoPoMapper userInfoPoMapper;

    private Long id = 30L;

    @Test
    public void insert() throws Exception {
        int count = 0;
        Long time = System.currentTimeMillis();
        UserInfoPo userInfoPo = new UserInfoPo();
        userInfoPo.setName("name");
        userInfoPo.setDeleted(DeleteEnum.NO_DELETE.getCode());
        userInfoPo.setBirthday(new Date(System.currentTimeMillis()));
        userInfoPo.setCreateTime(new Date(System.currentTimeMillis()));
        userInfoPo.setId(id);
        userInfoPo.setRemark("remark");
        userInfoPo.setSex(SexEnum.MAN.getCode());
        userInfoPo.setUpdateTime(new Date(System.currentTimeMillis()));
        userInfoPo.setWeight(79.2D);
        userInfoPo.setStatus(StatusEnum.ACTIVE.getCode());

        count = userInfoPoMapper.insert(userInfoPo);
        assertEquals(1, count);
        System.out.printf("==========================================UserInfoPoMapper01Test.insert%s", JsonUtils.toJson(userInfoPo));
    }


    @Test
    @DataSet(value = {"db/data/user_info.yml"})
    public void queryById() throws Exception {
        Long id = 9L;
        UserInfoPo userInfoPo = userInfoPoMapper.selectById(id);
        Assertions.assertThat(userInfoPo.getId()).isEqualTo(id);
        System.out.printf("==========================================UserInfoPoMapper01Test.queryById%s", JsonUtils.toJson(userInfoPo));
    }
}
