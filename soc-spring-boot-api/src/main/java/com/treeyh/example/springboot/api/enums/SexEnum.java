package com.treeyh.example.springboot.api.enums;

/**
 * @author TreeYH
 * @version 1.0
 * @description 性别枚举
 * @create 2019-05-21 17:16
 */
public enum SexEnum {

    UNKNOWN(0, "未知"),
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer code;
    private String desc;

    SexEnum(Integer code, String desc) {
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