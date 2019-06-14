package com.treeyh.example.springboot.worker.task;

import com.treeyh.common.web.context.HttpContext;
import com.treeyh.common.web.utils.IpUtils;
import com.treeyh.common.zookeeper.ZooKeeperDataWatcher;
import com.treeyh.example.springboot.common.config.SysConfig;
import com.treeyh.example.springboot.manager.domain.repository.RepositoryContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-22 10:52
 */
@Component
public class BaseWorker {


    private static final Logger logger = LoggerFactory.getLogger(BaseWorker.class);

    private static volatile ZooKeeperDataWatcher zooKeeperDataWatcher;

    private static volatile boolean checkType = true;

    @Autowired
    private RepositoryContext repositoryContext;

    public boolean checkZookeeper() {
        SysConfig sysConfig = repositoryContext.getSysConfig();
        if (checkType) {
            // 启动初始化zookeeper注册
            synchronized (ZooKeeperDataWatcher.class) {
                if (checkType) {
                    try {
                        zooKeeperDataWatcher = new ZooKeeperDataWatcher(sysConfig.getZookeeperServer(),
                                sysConfig.getZookeeperSessionTime(), sysConfig.getZookeeperPath(),
                                IpUtils.getServerIpAddress(), sysConfig.getServerPort());
                    } catch (IOException | InterruptedException e) {
                        logger.error(e.getMessage(), e);
                        return false;
                    }
                    checkType = false;
                }
            }
        }

        // 判断是否需要本机运行
        if (!zooKeeperDataWatcher.getIsThisRun()) {
            String info = zooKeeperDataWatcher.getData(repositoryContext.getSysConfig().getZookeeperPath());
            logger.info("_trace_id:" + HttpContext.getTraceId() + "数据归档 其他服务器正在运行: " + info);
            return false;
        }
        return true;
    }

}
