package com.treeyh.example.springboot.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 11:04
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.treeyh.example.springboot.dao", sqlSessionFactoryRef = "sqlSessionFactoryBean")
public class DataSourceConfig implements TransactionManagementConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value(value = "classpath:bean/mybatis/mapping/*.xml")
    private Resource[] mapperLocations;

    @Value(value = "classpath:mybatis-config.xml")
    private Resource configLocation;


//    @Value("${spring.datasourceType}")
//    private Class<? extends DataSource> datasourceType;


    @Primary
    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setMapperLocations(mapperLocations);
        ssfb.setConfigLocation(configLocation);
        ssfb.setTypeAliasesPackage("com.treeyh.example.springboot.dao");
        return ssfb;
    }

    @Primary
    @Bean(name = "dataSource", destroyMethod = "close") //initMethod = "init",
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {

        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}