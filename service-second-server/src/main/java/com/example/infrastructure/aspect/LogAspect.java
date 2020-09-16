package com.example.infrastructure.aspect;

import com.alibaba.fastjson.JSON;
import com.example.infrastructure.util.log.LogKV;
import com.example.infrastructure.util.log.LogMessage;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C)
 * FileName: LogAspect
 *
 * @author: yufeng
 * @date: 2020/7/25 14:03
 * @description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.example.*Client.*(..))")
    public void pointCut(){};

    @Pointcut("within(com.example.*.service..*)")
    public void log() {}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        log.info("method: {},入参：{}", joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @Before("log()")
    public void logAround(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            String[] paramStrs = new String[args.length];
            for (int i = args.length - 1; i >= 0; i--) {
                paramStrs[i] = args[i].toString();
            }

            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            LogMessage logMessage = new LogMessage();
            logMessage.setDltag("request_in");
            List<LogKV> logKVList = Lists.newArrayList();
            LogKV classLog = new LogKV("SecondKillRequest", className + "." + methodName);
            logKVList.add(classLog);

            int index = 1;
            for (String paramStr : paramStrs) {
                try {
                    Map<String, Object> map = JSON.parseObject(paramStr, Map.class);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        LogKV logKV = new LogKV(key, value);
                        logKVList.add(logKV);
                    }

                } catch(Exception e) {
                    String key = "arg" + index;
                    LogKV logKV = new LogKV(key, paramStr);
                    logKVList.add(logKV);
                }
                index++;
            }
            logMessage.setLogElements(logKVList);
            log.info(logMessage.toString());
        }
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        log.info("method: {}, 返回结果：{}", joinPoint.getSignature().getName(), result);
        Assert.notNull(result, "接口数据异常！");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.error("method: {}, 入参：{}, 异常信息：{}", joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs())
                , exception);
    }
}
