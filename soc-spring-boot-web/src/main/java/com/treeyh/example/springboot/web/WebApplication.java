package com.treeyh.example.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 12:02
 */
@SpringBootApplication(scanBasePackages = {"com.treeyh.example.springboot", "com.treeyh.common"})
@EnableAsync
@EnableSwagger2
public class WebApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

    // war启动入口
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class).bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(WebApplicationType.SERVLET);
    }

    // jar启动入口
    public static void main(String[] args) throws Exception {
        logger.info("Application start....");
        ConfigurableApplicationContext context = configureApplication(new SpringApplicationBuilder()).run(args);

        logger.info("Application start over.");
    }
}

