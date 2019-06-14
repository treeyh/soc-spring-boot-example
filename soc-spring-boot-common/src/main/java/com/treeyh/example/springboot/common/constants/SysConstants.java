package com.treeyh.example.springboot.common.constants;

/**
 * @author TreeYH
 * @version 1.0
 * @description 系统常量
 * @create 2019-05-21 10:46
 */
public class SysConstants {


    /**
     * 默认分页页码
     */
    public static final Long DEFAULT_PAGE_NUM = 1L;

    /**
     * 默认分页size
     */
    public static final Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 最大分页size
     */
    public static final Long MAX_PAGE_SIZE = 1000L;

    /**
     * 缓存key前缀
     */
    public static final String CACHE_KEY_PRE = "treeyh:example:springboot:";

    /**
     * 缓存超时时间
     */
    public static final Long CACHE_TIME_OUT = 5 * 60L;


    /**
     * 需要做签名校验
     */
    public static final Integer APP_CHECK_STATUS_CHECK = 1;

    /**
     * 不需要做签名校验
     */
    public static final Integer APP_CHECK_STATUS_NOT_CHECK = 2;

    /**
     * 不允许接口调用
     */
    public static final Integer APP_CHECK_STATUS_NOT_ACCESS = 3;

}
