package com.example.infrastructure.util.shardingsphere.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright (C), 2020
 * FileName: Master1Property
 *
 * @author: yufeng
 * @date: 2020/9/17 14:39
 * @description:
 */
@ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master1")
@Data
public class Master1Property {
    private String url;
    private String username;
    private String password;
    private String type;
    private String driverClassName;
}
