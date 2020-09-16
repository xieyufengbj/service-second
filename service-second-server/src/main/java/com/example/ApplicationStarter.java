package com.example;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.example.infrastructure.util.shardingsphere.config.ShardingSphereTransactionConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

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
@Import(ShardingSphereTransactionConfiguration.class)
@SpringBootApplication(exclude= {JtaAutoConfiguration.class, DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class})
public class ApplicationStarter {

    /**
     * 项目启动方法
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationStarter.class, args);

        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String name : beans) {
            System.out.println("++++++" + name);
        }
//        ExampleExecuteTemplate.run(applicationContext.getBean(ExampleService.class));
    }
}
