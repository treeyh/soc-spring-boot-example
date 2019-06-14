package com.treeyh.example.springboot.api.model.base.resp;

import com.treeyh.common.model.result.ResultCode;
import com.treeyh.common.model.result.ResultCodeInfo;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 10:06
 */
public class SysResultCode extends ResultCode {

    public static final ResultCodeInfo APP_INFO_EXCEPTION = new ResultCodeInfo(100001001, "APP_INFO_EXCEPTION", "app信息为空或不可用或无权限访问接口");
    public static final ResultCodeInfo REQUEST_TIME_RANGE_ERROR = new ResultCodeInfo(100001002, "REQUEST_TIME_RANGE_ERROR", "请求时间戳超过有效范围");
    public static final ResultCodeInfo REQUEST_SIGN_ERROR = new ResultCodeInfo(100001003, "REQUEST_SIGN_ERROR", "请求签名错误");

    public static final ResultCodeInfo SOCKET_TIMEOUT_ERROR = new ResultCodeInfo(100001004, "SOCKET_TIMEOUT_ERROR", "服务调用超时");


    public static final ResultCodeInfo PARAM_ERROR = new ResultCodeInfo(100002001, "PARAM_ERROR", "参数异常");
    public static final ResultCodeInfo PARAM_IS_NULL = new ResultCodeInfo(100002002, "PARAM_IS_NULL", "%s 参数为空");
    public static final ResultCodeInfo PARAM_ERROR_NAME = new ResultCodeInfo(100002003, "PARAM_ERROR_NAME", "%s 参数异常");
    public static final ResultCodeInfo PARAM_ERROR_NAME_MSG = new ResultCodeInfo(100002004, "PARAM_ERROR_NAME_MSG", "%s 参数错误应为%s");
    public static final ResultCodeInfo PARAM_RANGE_ERROR = new ResultCodeInfo(100002005, "PARAM_RANGE_ERROR", "%s 参数允许输入范围为%s-%s");


    public static final ResultCodeInfo USER_INFO_EXIST = new ResultCodeInfo(100003001, "USER_INFO_EXIST", "用户对象已存在");
    public static final ResultCodeInfo USER_INFO_NOT_EXIST = new ResultCodeInfo(100003002, "USER_INFO_NOT_EXIST", "用户对象不存在");



}