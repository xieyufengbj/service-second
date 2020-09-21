package com.example.infrastructure.util.queue.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020
 * FileName: ParamConfigService
 *
 * @author: yufeng
 * @date: 2020/9/14 08:52
 * @description:
 */
//@Service
public class ParamConfigService {

    @Value("${rocket.group}")
    public String rocketGroup ;
    @Value("${rocket.topic}")
    public String rocketTopic ;
    @Value("${rocket.tag}")
    public String rocketTag ;
}
