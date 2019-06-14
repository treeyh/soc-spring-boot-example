package com.treeyh.example.springboot.api.enums;

/**
 * @author TreeYH
 * @version 1.0
 * @description 性别枚举
 * @create 2019-05-21 17:16
 */
public enum SexEnum {

    UNKNOWN((byte)0, "未知"),
    MAN((byte)1, "男"),
    WOMAN((byte)2, "女");

    private Byte code;
    private String desc;

    SexEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}