package com.example.infrastructure.task;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Copyright (C)
 * FileName: BloomFilterTask
 *
 * @author: yufeng
 * @date: 2020/8/24 11:13
 * @description:
 */
@Component
public class BloomFilterTask {


    @PostConstruct
    public void initBloomFilterCache() {
        System.out.println("==============initBloomFilterCache===============");
    }
}
