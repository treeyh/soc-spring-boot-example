package com.treeyh.example.springboot.api.enums;

/**
 * @author TreeYH
 * @version 1.0
 * @description 删除状态枚举
 * @create 2019-05-21 17:12
 */
public enum DeleteEnum {

    DELETED(1, "已删除"),
    NO_DELETE(2, "未删除");

    private Integer code;
    private String desc;

    DeleteEnum(Integer code, String desc) {
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