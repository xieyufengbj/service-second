package com.example.infrastructure.util.redis;

/**
 * Copyright (C)
 * FileName: KeyPrefix
 *
 * @author: yufeng
 * @date: 2020/8/6 10:06
 * @description:
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
