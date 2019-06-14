package com.treeyh.example.springboot.worker.task;

import com.treeyh.common.utils.ThreadUtils;
import com.treeyh.common.web.context.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-22 11:31
 */
@Component
public class UserStatusWorker {

    private static final Logger logger = LoggerFactory.getLogger(UserStatusWorker.class);


    @Autowired
    private BaseWorker baseWorker;

    /**
     * 通知用户
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void runWorker() {
        HttpContext.start();

//        if (!baseWorker.checkZookeeper()) {
//            logger.info("_traceId:{}===>InitHeadMasterLimitWorker[initHeadMasterLimit]===>not register node", HttpContext.getTraceId());
//            return;
//        }

        long begin = System.currentTimeMillis();
        logger.info("UserStatusWorker[runWorker]===>start,begin:{}", begin);

        System.out.println("UserStatusWorker runWorker run.... ");
        ThreadUtils.sleep(5000L);

        logger.info("UserStatusWorker[runWorker]===>end,time:{}", (System.currentTimeMillis() - begin));
    }
}
