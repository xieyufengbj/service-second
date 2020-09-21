package com.example.infrastructure.util.shardingsphere.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright (C), 2020
 * FileName: Slave0Property
 *
 * @author: yufeng
 * @date: 2020/9/17 14:40
 * @description:
 */
@ConfigurationProperties(prefix = "spring.shardingsphere.datasource.slave0")
@Data
public class Slave0Property {

    private String url;

    private String username;

    private String password;

    private String type;

    private String driverClassName;
}
