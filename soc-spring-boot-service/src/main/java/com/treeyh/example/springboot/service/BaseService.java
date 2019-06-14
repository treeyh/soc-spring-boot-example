package com.treeyh.example.springboot.service;

import com.treeyh.common.model.LogModel;
import com.treeyh.common.model.result.CallResult;
import com.treeyh.common.model.result.ResultCodeInfo;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TreeYH
 * @version 1.0
 * @description service模块基础类
 * @create 2019-05-21 11:30
 */
public class BaseService {
    protected static Logger logger = LoggerFactory.getLogger(BaseService.class);


    protected <T> CallResult<T> makeFailCallResult(LogModel logModel, ResultCodeInfo resultCodeInfo, T obj, Throwable throwable, Object...args){
        logModel.addMetaDataError(throwable.getMessage());
        logger.error(logModel.toJson(), throwable);
        return  this.makeResult(false, resultCodeInfo, obj, throwable, args);
    }

    protected  <T> CallResult<T> makeResult(Boolean isSuccess, ResultCodeInfo resultCodeInfo, T obj, Throwable throwable, Object...args) {
        return CallResult.makeCallResult(isSuccess, resultCodeInfo,  obj, throwable, args);
    }

    protected  <T> CallResult<T> makeSuccessResult(T obj) {
        return CallResult.makeCallResult(true, SysResultCode.SUCCESS,  obj, null);
    }


    protected void writeLog(LogModel lm) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson());
        }
    }

    protected void writeLog(LogModel lm, boolean isClear) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson(isClear));
        }
    }

    protected void writeLog(String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    protected void writeErrorLog(LogModel lm, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(lm.toJson(false), e);
        }
    }

    protected void writeLog(Logger logger, LogModel lm) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson());
        }
    }

    protected void writeLog(Logger logger, LogModel lm, boolean isClear) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson(isClear));
        }
    }

    protected void writeLog(Logger logger, String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    protected void writeErrorLog(Logger logger, LogModel lm, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(lm.toJson(false), e);
        }
    }

}
