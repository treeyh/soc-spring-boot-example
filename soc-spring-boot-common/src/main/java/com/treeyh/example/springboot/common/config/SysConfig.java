package com.treeyh.example.springboot.common.config;

import com.treeyh.common.web.SocCommonWebConfig;
import com.treeyh.common.web.context.AppBeanContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 10:45
 */
@Component
public class SysConfig  extends AppBeanContext {


    @Value("${spring.redis.key-pre}")
    private String cacheKeyPre;

    @Value("${spring.redis.app-time-out}")
    private Integer cacheAppTimeOut;



    /**
     * 启动端口
     */
    @Value("${server.port:0}")
    private int serverPort;

    /**
     * zk服务器
     */
    @Value("${zookeeper.server:}")
    private String zookeeperServer;

    /**
     * session超时时间
     */
    @Value("${zookeeper.sessionTime:3000}")
    private Integer zookeeperSessionTime;

    /**
     * zk 计划任务锁 路径
     */
    @Value("${zookeeper.path:}")
    private String zookeeperPath;


    /**
     * session超时时间
     */
    @Value("${soc.web.checkSign.status:2}")
    private Integer checkSignStatus;

    /**
     * session超时时间
     */
    @Value("${soc.web.checkSign.timeRange:600}")
    private Long timeRange;

    /**
     * zk 计划任务锁 路径
     */
    @Value("${soc.web.checkSign.magicSign:}")
    private String magicSign;


    public String getZookeeperServer() {
        return zookeeperServer;
    }

    public Integer getZookeeperSessionTime() {
        return zookeeperSessionTime;
    }

    public String getZookeeperPath() {
        return zookeeperPath;
    }

    public int getServerPort() {
        return serverPort;
    }


    public Integer getCheckSignStatus() {
        return checkSignStatus;
    }

    public Long getTimeRange() {
        return timeRange;
    }

    public String getMagicSign() {
        return magicSign;
    }

    /**
     * 获取appInfo缓存key
     *
     * @param appId
     * @return
     */
    public String getAppInfoCacheKey(String appId) {
        return this.cacheKeyPre + ":AppInfo:" + appId;
    }

    public Integer getCacheAppTimeOut() {
        return cacheAppTimeOut;
    }
}
