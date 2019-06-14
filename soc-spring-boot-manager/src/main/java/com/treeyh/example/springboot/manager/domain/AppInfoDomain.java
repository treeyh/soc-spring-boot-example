package com.treeyh.example.springboot.manager.domain;

import com.treeyh.common.utils.JsonUtils;
import com.treeyh.example.springboot.api.enums.StatusEnum;
import com.treeyh.example.springboot.common.constants.SysConstants;
import com.treeyh.example.springboot.manager.bo.AppInfoBo;
import com.treeyh.example.springboot.manager.domain.repository.RepositoryContext;
import org.apache.commons.lang3.StringUtils;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-25 15:05
 */
public class AppInfoDomain {

    private RepositoryContext repositoryContext;

    public  AppInfoDomain(RepositoryContext repositoryContext){
        this.repositoryContext = repositoryContext;
    }


    public AppInfoBo queryByAppId(String appId){
        if(StringUtils.isEmpty(appId)){
            return null;
        }

        String cacheKey = this.repositoryContext.getSysConfig().getAppInfoCacheKey(appId);

        String json = this.repositoryContext.getRedisHelper().get(cacheKey);

        AppInfoBo appInfoBo = null;

        if(StringUtils.isEmpty(json)){
            appInfoBo = this.repositoryContext.getAppInfoRepository().queryByAppId(appId);

            if(null == appInfoBo){
                appInfoBo = new AppInfoBo();
                appInfoBo.setAppId(appId);
                appInfoBo.setCheckStatus(SysConstants.APP_CHECK_STATUS_NOT_ACCESS);
                appInfoBo.setStatus(StatusEnum.NO_ACTIVE.getCode());
            }

            this.repositoryContext.getRedisHelper().set(cacheKey, JsonUtils.toJson(appInfoBo),
                    this.repositoryContext.getSysConfig().getCacheAppTimeOut());
        }else{
            appInfoBo = JsonUtils.fromJson(json, AppInfoBo.class);
        }

        return appInfoBo;
    }

}
