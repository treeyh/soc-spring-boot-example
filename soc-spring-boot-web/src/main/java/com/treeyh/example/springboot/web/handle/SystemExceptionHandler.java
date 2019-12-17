package com.treeyh.example.springboot.web.handle;

import com.treeyh.common.constants.SocCommonConstants;
import com.treeyh.common.exception.SysErrorException;
import com.treeyh.common.model.result.ResultCodeInfo;
import com.treeyh.common.model.result.ReturnResult;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * @author TreeYH
 * @version 1.0
 * @description 全局异常处理类
 * @create 2019-05-21 14:14
 */
@ControllerAdvice
public class SystemExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    private static Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");


    /**
     * 系统异常处理
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnResult<Object> defaultErrorHandler(final HttpServletRequest req, HttpServletResponse resp, final Exception e) throws Exception {
        logger.error("拦截到错误:"+e.getMessage(), e);
        /*  使用response返回    */
        /**
         * 设置状态码
         */
        resp.setStatus(HttpStatus.OK.value());
        /**
         * 设置ContentType
         */
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        /**
         * 避免乱码
         */
        resp.setCharacterEncoding(SocCommonConstants.UTF8);
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");

        ResultCodeInfo resultCodeInfo = null;
        String cause = null;
        if(e instanceof NoHandlerFoundException){
            NoHandlerFoundException ne = (NoHandlerFoundException)e;
            cause = ne.getMessage();
            logger.error("Path Not Found!" + ne.getMessage(), ne);
            resultCodeInfo = SysResultCode.PATH_NOT_FOUND;
        }else if(e instanceof SysErrorException) {
            SysErrorException de = (SysErrorException) e;
            cause = de.getMessage();
            logger.error("SysErrorException Error reason!" + de.getMessage(), de);
            resultCodeInfo = de.getResultCodeInfo();
        }else if(e instanceof BindException) {
            BindException be = (BindException)e;
            cause = be.getMessage();
            resultCodeInfo = SysResultCode.PARAM_ERROR;
            logger.error("ExceptionHandler param error"+ e.getMessage(), e);
        }else {
            logger.error("ExceptionHandler sys exception"+ e.getMessage(), e);
            cause = e.getMessage();
            resultCodeInfo = SysResultCode.SYS_ERROR_MSG;
        }
        return new ReturnResult(resultCodeInfo, cause);
    }

    private static boolean isContainsChinese(String str) {
        if (str != null) {
            return pat.matcher(str).find();
        }
        return false;
    }
}

