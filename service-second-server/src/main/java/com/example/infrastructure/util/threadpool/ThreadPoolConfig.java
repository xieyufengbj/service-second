package com.example.infrastructure.util.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Copyright (C), 2020, 上海昌投网络科技有限公司
 * FileName: ThreadPoolConfig
 *
 * @author: yufeng
 * @date: 2020/9/13 16:04
 * @description:
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolConfig {
    @Autowired
    private ThreadPoolProperties threadPoolProperties;

    @Bean
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 配置最大线程数
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 配置队列大小
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 允许线程的空闲时间 超过将销毁
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-executor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程池关闭的时候等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置等待时间，超过则丢弃未完成的任务
        executor.setAwaitTerminationSeconds(threadPoolProperties.getAwaitTerminationSeconds());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
