package com.treeyh.example.springboot.api.model.base;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author TreeYH
 * @version 1.0
 * @description 用户基础对象
 * @create 2019-05-21 14:37
 */
public class UserInfoBase {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 性别，0未知，1男，2女
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 记录状态,1有效，0无效
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


    public UserInfoBase() {
    }

    public UserInfoBase(Long id, String name, Integer sex, Date birthday, Double weight, Integer status, String remark) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.weight = weight;
        this.status = status;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}
