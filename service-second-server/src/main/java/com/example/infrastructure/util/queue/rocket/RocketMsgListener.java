package com.example.infrastructure.util.queue.rocket;

import com.example.infrastructure.util.queue.service.impl.ParamConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2020, 上海昌投网络科技有限公司
 * FileName: RocketMsgListener
 *
 * @author: yufeng
 * @date: 2020/9/14 08:55
 * @description:
 */
//@Component
@Slf4j
public class RocketMsgListener implements MessageListenerConcurrently {

    @Resource
    private ParamConfigService paramConfigService ;
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("接受到的消息为："+new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume ==3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if(messageExt.getTopic().equals(paramConfigService.rocketTopic)){
            String tags = messageExt.getTags() ;
            switch (tags){
                case "rocketTag":
                    log.info("开户 tag == >>"+tags);
                    break ;
                default:
                    log.info("未匹配到Tag == >>"+tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
