package com.example;

import com.example.domain.shardingsphere.service.OrderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
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
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, JtaAutoConfiguration.class})
public class ApplicationStarter {

    /**
     * 项目启动方法
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationStarter.class, args);
        OrderServiceImpl exampleService = applicationContext.getBean(OrderServiceImpl.class);

        try {
            exampleService.initEnvironment();
            exampleService.processSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exampleService.cleanEnvironment();
        }
    }
}
