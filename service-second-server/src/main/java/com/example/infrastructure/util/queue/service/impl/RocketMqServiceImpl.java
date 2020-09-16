package com.example.infrastructure.util.queue.service.impl;

import com.example.infrastructure.util.queue.service.RocketMqService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (C), 2020, 上海昌投网络科技有限公司
 * FileName: RocketMqServiceImpl
 *
 * @author: yufeng
 * @date: 2020/9/14 08:49
 * @description:
 */
//@Service
public class RocketMqServiceImpl implements RocketMqService {
    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService ;
    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.rocketTopic,
                    paramConfigService.rocketTag,
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
