package com.example.infrastructure.util.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright (C), 2020
 * FileName: ThreadPoolProperties
 *
 * @author: yufeng
 * @date: 2020/9/13 16:03
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "async.executor")
public class ThreadPoolProperties {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;
    private String prefix;
    private Integer keepAliveSeconds;
    private Integer awaitTerminationSeconds;
}
