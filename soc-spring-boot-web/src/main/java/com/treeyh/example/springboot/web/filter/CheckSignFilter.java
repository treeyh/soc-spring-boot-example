package com.treeyh.example.springboot.web.filter;

import com.treeyh.common.model.result.ReturnResult;
import com.treeyh.common.utils.DateUtils;
import com.treeyh.common.utils.JsonUtils;
import com.treeyh.common.utils.MD5Utils;
import com.treeyh.common.utils.StreamUtils;
import com.treeyh.common.web.context.HttpContext;
import com.treeyh.common.web.context.RequestWrapper;
import com.treeyh.common.web.context.ResponseWrapper;
import com.treeyh.common.web.filter.BaseFilter;
import com.treeyh.example.springboot.api.enums.StatusEnum;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import com.treeyh.example.springboot.common.constants.SysConstants;
import com.treeyh.example.springboot.manager.bo.AppInfoBo;
import com.treeyh.example.springboot.manager.domain.AppInfoDomain;
import com.treeyh.example.springboot.manager.domain.repository.RepositoryContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author TreeYH
 * @version 1.0
 * @description 校验签名
 * @create 2019-05-25 14:50
 */
//@orgder(5)
//@Component
public class CheckSignFilter extends BaseFilter {


    private static final Logger logger = LoggerFactory.getLogger(CheckSignFilter.class);

    @Autowired
    private RepositoryContext repositoryContext;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        RequestWrapper requestWrapper = (RequestWrapper)httpServletRequest;
        ResponseWrapper responseWrapper = (ResponseWrapper) httpServletResponse;

        //获取相对URI
        String requestURI = requestWrapper.getRequestURI();

        // 如果是swagger请求则直接返回
        if (this.isSwaggerReq(requestURI) || !repositoryContext.getSysConfig().getCheckSignStatus().equals(StatusEnum.ACTIVE.getCode())) {
            logger.info(" request not check sign requestUrl:" + requestURI);
            filterChain.doFilter(requestWrapper, responseWrapper);
            return;
        }

        String appId = httpServletRequest.getParameter("appId");
        if(StringUtils.isEmpty(appId)){
            //判断appId是否存在

            logger.info("_traceId=" + HttpContext.getTraceId() + " request params appId not exist. requestUrl:" + requestURI);
            this.responseEnd(responseWrapper, JsonUtils.toJson(ReturnResult.error(SysResultCode.PARAM_ERROR_NAME, "appId")));
            return;
        }

        //获取应用信息
        AppInfoDomain appInfoDomain = new AppInfoDomain(repositoryContext);
        AppInfoBo appInfoBo = appInfoDomain.queryByAppId(appId);

        if(null == appInfoBo || !StatusEnum.ACTIVE.getCode().equals(appInfoBo.getStatus())
                || SysConstants.APP_CHECK_STATUS_NOT_ACCESS.equals(appInfoBo.getCheckStatus())){
            //无法获取应用信息或不可用，或应用不允许访问web接口

            logger.info("_traceId=" + HttpContext.getTraceId() + " app 信息为空或不可用或无权限访问接口. appid:" + appId);
            this.responseEnd(responseWrapper, JsonUtils.toJson(ReturnResult.error(SysResultCode.APP_INFO_EXCEPTION)));
            return;
        }

        String magic = requestWrapper.getParameter("magic");

        //判断应用是否需要验证签名
        if(this.needCheckSign(appInfoBo.getCheckStatus())){

            if(!this.checkTime(requestWrapper)
                    && !this.repositoryContext.getSysConfig().getMagicSign().equals(magic)){
                this.responseEnd(responseWrapper, JsonUtils.toJson(ReturnResult.error(SysResultCode.REQUEST_TIME_RANGE_ERROR)));
                return;
            }
            if(!this.repositoryContext.getSysConfig().getMagicSign().equals(magic)
                    && !this.checkSign(appInfoBo, requestWrapper)){
                this.responseEnd(responseWrapper, JsonUtils.toJson(ReturnResult.error(SysResultCode.REQUEST_SIGN_ERROR)));
                return;
            }
        }

        filterChain.doFilter(requestWrapper, responseWrapper);
    }

    @Override
    public void destroy() {

    }


    /**
     * 校验时间差是否在范围内
     * @param requestWrapper
     * @return
     */
    private Boolean checkTime(RequestWrapper requestWrapper){

        String time = requestWrapper.getParameter("timestamp");

        Date date = DateUtils.strToDate2(time);

        if(null == date){
            return false;
        }

        Long lag = Math.abs(System.currentTimeMillis() - date.getTime());

        if(this.repositoryContext.getSysConfig().getTimeRange() >= lag){
            return true;
        }
        return false;
    }


    /**
     * 校验签名是否有效
     * @param appInfoBo
     * @param requestWrapper
     * @return
     * @throws IOException
     */
    private Boolean checkSign(AppInfoBo appInfoBo, RequestWrapper requestWrapper) throws IOException {

        String appId = requestWrapper.getParameter("appId");
        String sign = requestWrapper.getParameter("sign");
        String time = requestWrapper.getParameter("timestamp");

        Map<String, String[]> params = requestWrapper.getParameterMap();
        List<String> keys = new ArrayList<String>();
        keys.addAll(params.keySet());
        Collections.sort(keys);

        logger.info("_traceId=" + HttpContext.getTraceId() + "request params sort:" + JsonUtils.toJson(keys));

        StringBuilder sb = new StringBuilder(appId);
        sb.append("&").append(time);

        for(String key : keys){
            if(key.equals("appId") || key.equals("sign") || key.equals("timestamp")){
                continue;
            }
            sb.append("&").append(key).append("=").append(requestWrapper.getParameter(key));
        }

        String charEncoding = requestWrapper.getCharacterEncoding() != null ?
                requestWrapper.getCharacterEncoding() : "UTF-8";
        String body = StreamUtils.getStringByStream(requestWrapper.getInputStream(), charEncoding);

        if(!StringUtils.isEmpty(body)){
            sb.append("&").append(body);
        }

        String p = sb.append("&").toString();

        String md5str = p + appInfoBo.getAppSecret1();
        String md5 = MD5Utils.getMD5(md5str);
        if(md5.equals(sign)){
            logger.info("request check sign success sign:"+sign+"; md5str:"+md5str+"; md5-1:"+md5);
            return true;
        }
        logger.info("request check sign fail sign:"+sign+"; md5str:"+md5str+"; md5-1:"+md5);

        md5str = p + appInfoBo.getAppSecret2();
        md5 = MD5Utils.getMD5(md5str);
        if(md5.equals(sign)){
            logger.info("request sign:"+sign+"; md5str:"+md5str+"; md5-2:"+md5);
            return true;
        }
        logger.info("request check sign fail sign:"+sign+"; md5str:"+md5str+"; md5-2:"+md5);

        return false;
    }

    /**
     * 判断是否是swagger-ui发来的请求
     *
     * @param requestUri
     * @return
     */
    public Boolean isSwaggerReq(String requestUri) {
        return this.isSwaggerUiReq(requestUri) || this.isApiDocsReq(requestUri);
    }

    public Boolean isSwaggerUiReq(String requestUri)
    {
        return requestUri.contains("swagger");
    }

    public Boolean isApiDocsReq(String requestUri)
    {
        return requestUri.equals("/v2/api-docs");
    }


    /**
     * 判断是否需要检查签名
     * @param checkStatus
     * @return
     */
    public Boolean needCheckSign(Integer checkStatus)
    {
        return !SysConstants.APP_CHECK_STATUS_NOT_CHECK.equals(checkStatus);
    }
}
