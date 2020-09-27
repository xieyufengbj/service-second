package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.SQLException;

/**
 * Copyright (C)
 * FileName: SecondApplication
 *
 * @author: yufeng
 * @date: 2020/7/22 18:06
 * @description:
 */
//@EnableFeignClients
@EnableEurekaClient
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.example.domain.shardingsphere.repository"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, JtaAutoConfiguration.class})
public class ApplicationStarter {

    /**
     * 项目启动方法
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationStarter.class, args);
    }
}
