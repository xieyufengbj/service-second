package com.example.infrastructure.util.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C)
 * FileName: CustomExecutor
 *
 * @author: yufeng
 * @date: 2020/8/25 15:02
 * @description:
 */
public class CustomExecutor {

    public static ExecutorService EVENT_BUS_WORKER_POOL =  new ThreadPoolExecutor(6, 12,
            2000L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue());
}
