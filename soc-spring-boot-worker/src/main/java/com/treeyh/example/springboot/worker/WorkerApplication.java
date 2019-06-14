package com.treeyh.example.springboot.worker;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-22 10:48
 */
@SpringBootApplication(scanBasePackages = {"com.treeyh.example.springboot", "com.treeyh.common"},
        exclude = SecurityAutoConfiguration.class)
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
public class WorkerApplication extends SpringBootServletInitializer {


    // war启动入口
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    // jar启动入口
    public static void main(String[] args) {
        ConfigurableApplicationContext context = configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(WorkerApplication.class).bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(WebApplicationType.SERVLET);
    }

}

