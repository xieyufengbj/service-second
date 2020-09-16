package com.example.infrastructure.util.eventbus;

import com.example.infrastructure.util.executor.CustomExecutor;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C)
 * FileName: EventBusConfig
 *
 * @author: yufeng
 * @date: 2020/8/25 14:37
 * @description:
 */
@Configuration
public class EventBusConfig {

    @Bean
    public AsyncEventBus asyncEventBus() {

        return new AsyncEventBus(CustomExecutor.EVENT_BUS_WORKER_POOL);
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}
