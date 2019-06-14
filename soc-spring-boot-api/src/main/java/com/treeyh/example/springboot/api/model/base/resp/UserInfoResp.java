package com.treeyh.example.springboot.api.model.base.resp;

import com.treeyh.example.springboot.api.model.base.UserInfoBase;

import java.util.Date;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 10:17
 */
public class UserInfoResp extends UserInfoBase {



    private Date createTime;

    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
