package com.treeyh.example.springboot.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.treeyh.common.exception.Validation;
import com.treeyh.common.model.LogModel;
import com.treeyh.common.model.result.CallResult;
import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.model.result.ReturnResult;
import com.treeyh.common.utils.ClazzConverterUtils;
import com.treeyh.common.utils.JsonUtils;
import com.treeyh.example.springboot.api.UserInfoFacade;
import com.treeyh.example.springboot.api.model.base.req.UserInfoReq;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import com.treeyh.example.springboot.api.model.base.resp.UserInfoResp;
import com.treeyh.example.springboot.common.constants.SysConstants;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import com.treeyh.example.springboot.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;


/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 14:23
 */
@RestController
public class UserInfoController implements UserInfoFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public ReturnResult<UserInfoResp> createUser(@RequestBody UserInfoReq userInfoReq) {

        LogModel lm = LogModel.newLogModel("UserInfoController.createDemo")
                .addMetaData("method", "POST")
                .addMetaData("userInfoReq", userInfoReq);

        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == userInfoReq), SysResultCode.PARAM_ERROR_NAME, "userInfoReq")
                .addError((StringUtils.isEmpty(userInfoReq.getName())), SysResultCode.PARAM_ERROR_NAME, "name")
                .isValidThrowException();

        UserInfoBo userInfoBo = ClazzConverterUtils.converterClass(userInfoReq, UserInfoBo.class);

        CallResult<UserInfoBo> callResult = userInfoService.createDemo(userInfoBo);
        lm.addMetaData("callResult", callResult);

        if (!callResult.isSuccess()) {
            logger.info(lm.toJson());

            return new ReturnResult<UserInfoResp>(callResult.getResultCodeInfo());
        }

        ReturnResult<UserInfoResp> returnResult = new ReturnResult<UserInfoResp>(
                ClazzConverterUtils.converterClass(callResult.getResult(), UserInfoResp.class), SysResultCode.SUCCESS);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult<UserInfoResp> queryById(@PathVariable("id") Long id) {
        LogModel lm = LogModel.newLogModel("UserInfoController.queryById")
                .addMetaData("method", "GET")
                .addMetaData("id", id);
        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == id), SysResultCode.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<UserInfoBo> callResult = userInfoService.queryById(id);
        lm.addMetaData("callResult", callResult);

        if (!callResult.isSuccess()) {
            logger.info(lm.toJson());
            return new ReturnResult<UserInfoResp>(callResult.getResultCodeInfo());
        }


        System.out.println("-------------:"+ JsonUtils.toJson(callResult.getResult()));

        UserInfoResp userInfoResp = ClazzConverterUtils.converterClass(callResult.getResult(), UserInfoResp.class);


        System.out.println("-------------:"+ JsonUtils.toJson(userInfoResp));

        ReturnResult<UserInfoResp> returnResult = ReturnResult.success(userInfoResp);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult deleteById(@PathVariable("id") Long id) {
        LogModel lm = LogModel.newLogModel("UserInfoController.deleteById")
                .addMetaData("method", "GET")
                .addMetaData("id", id);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), SysResultCode.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<Integer> callResult = userInfoService.deleteById(id);
        lm.addMetaData("callResult", callResult);

        if (!callResult.isSuccess()) {
            logger.info(lm.toJson());
            return new ReturnResult(callResult.getResultCodeInfo());
        }

        ReturnResult returnResult = ReturnResult.success();

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult updateById(@PathVariable("id") Long id, @RequestBody UserInfoReq userInfoReq) {
        LogModel lm = LogModel.newLogModel("UserInfoController.updateById")
                .addMetaData("method", "PUT")
                .addMetaData("id", id)
                .addMetaData("userInfoReq", userInfoReq);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), SysResultCode.PARAM_ERROR_NAME, "id")
                .addError((null == userInfoReq), SysResultCode.PARAM_ERROR_NAME, "userInfoReq")
                .addError((StringUtils.isEmpty(userInfoReq.getName())), SysResultCode.PARAM_ERROR_NAME, "name")
                .isValidThrowException();

        UserInfoBo userInfoBo = ClazzConverterUtils.converterClass(userInfoReq, UserInfoBo.class);

        CallResult<Integer> callResult = userInfoService.updateById(id, userInfoBo);
        lm.addMetaData("callResult", callResult);

        if (!callResult.isSuccess()) {
            logger.info(lm.toJson());
            return new ReturnResult(callResult.getResultCodeInfo());
        }

        ReturnResult returnResult = ReturnResult.success();

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult<PageResult<UserInfoResp>> queryByPage(@RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "status", required = false) Integer status,
                                                              @RequestParam("page") Long page,
                                                              @RequestParam("size") Long size) {
        LogModel lm = LogModel.newLogModel("UserInfoController.queryByPage")
                .addMetaData("method", "GET")
                .addMetaData("name", name)
                .addMetaData("status", status)
                .addMetaData("page", page)
                .addMetaData("size", size);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == page || 0 >= page), SysResultCode.PARAM_ERROR_NAME_MSG, "page", "正整数")
                .addError((null == size || 0 >= size || SysConstants.MAX_PAGE_SIZE < size),
                        SysResultCode.PARAM_RANGE_ERROR, "size", "1", SysConstants.MAX_PAGE_SIZE.toString())
                .isValidThrowException();

        CallResult<PageResult<UserInfoBo>> callResult = userInfoService.queryByPage(name, status, page, size);

        if (!callResult.isSuccess()) {
            logger.info(lm.toJson());
            return new ReturnResult<PageResult<UserInfoResp>>(callResult.getResultCodeInfo());
        }

        ReturnResult<PageResult<UserInfoResp>> returnResult = ReturnResult.success(
                ClazzConverterUtils.converterClass(callResult.getResult(), new TypeReference<PageResult<UserInfoResp>>() {}));


        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

}