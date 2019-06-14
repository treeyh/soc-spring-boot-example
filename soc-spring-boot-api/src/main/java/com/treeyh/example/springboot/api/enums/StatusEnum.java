package com.treeyh.example.springboot.api.enums;

/**
 * @author TreeYH
 * @version 1.0
 * @description 状态枚举
 * @create 2019-05-21 20:48
 */
public enum StatusEnum {

    ACTIVE(1, "可用"),
    NO_ACTIVE(2, "不可用");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}