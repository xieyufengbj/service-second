package com.example.infrastructure.util.queue.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * Copyright (C), 2020, 上海昌投网络科技有限公司
 * FileName: RocketMqService
 *
 * @author: yufeng
 * @date: 2020/9/14 08:44
 * @description:
 */
public interface RocketMqService {

    SendResult openAccountMsg(String msgInfo);
}
