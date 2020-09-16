package com.example.infrastructure.util.log;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C)
 * FileName: LogKV
 *
 * @author: yufeng
 * @date: 2020/8/2 14:31
 * @description:
 */
@Data
public class LogKV {
    private String key;
    private String value;

    public LogKV(String key, Object value) {
        this.key = key;
        if (value == null) {
            value = "";
        }

        if (!(value instanceof List) && !(value instanceof Map)) {
            this.value = value.toString();
        } else {
            this.value = JSON.toJSONString(value);
        }
    }
}
