package com.treeyh.example.springboot.manager.domain.repository;

import com.treeyh.common.web.SocCommonWebConfig;
import com.treeyh.common.web.cache.RedisHelper;
import com.treeyh.example.springboot.common.config.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 11:07
 */
@Component
public class RepositoryContext {


    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AppInfoRepository appInfoRepository;

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private SocCommonWebConfig socCommonWebConfig;


    public SysConfig getSysConfig() {
        return sysConfig;
    }

    public RedisHelper getRedisHelper() {
        return redisHelper;
    }
    public UserInfoRepository getUserInfoRepository() {
        return userInfoRepository;
    }

    public AppInfoRepository getAppInfoRepository() {
        return appInfoRepository;
    }
}
