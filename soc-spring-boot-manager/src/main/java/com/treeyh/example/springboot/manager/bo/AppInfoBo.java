package com.treeyh.example.springboot.manager.bo;

import java.util.Date;

/**
 * @author TreeYH
 * @version 1.0
 * @description 应用信息业务对象
 * @create 2019-05-25 15:08
 */
public class AppInfoBo {

    private Long id;

    private String appId;

    private String appName;

    private String appSecret1;

    private String appSecret2;

    private String owner;

    private String appUrl;

    private Integer checkStatus;

    private Integer status;

    private String remark;

    private Integer isDeleted;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSecret1() {
        return appSecret1;
    }

    public void setAppSecret1(String appSecret1) {
        this.appSecret1 = appSecret1;
    }

    public String getAppSecret2() {
        return appSecret2;
    }

    public void setAppSecret2(String appSecret2) {
        this.appSecret2 = appSecret2;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
