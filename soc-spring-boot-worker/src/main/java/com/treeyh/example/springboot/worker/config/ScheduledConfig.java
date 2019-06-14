package com.treeyh.example.springboot.worker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-22 10:51
 */
@Component
public class ScheduledConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }


    @Bean(name = "taskScheduler", destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler taskScheduler() {
        //创建一个线程池调度器
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //设置线程池容量
        //允许情况下有多少频繁发生任务就有多大
        scheduler.setPoolSize(5);
        //线程名前缀
        scheduler.setThreadNamePrefix("task-");
        //当shutdown后等待处理时常
        scheduler.setAwaitTerminationSeconds(60);
        //当调度器shutdown被调用时等待当前被调度的任务完成
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        //设置当任务被取消的同时从当前调度器移除的策略
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.initialize();
        return scheduler;
    }
}

