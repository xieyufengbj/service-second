package com.example.infrastructure.access;

import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C)
 * FileName: AccessInterceptor
 *
 * @author: yufeng
 * @date: 2020/7/25 12:11
 * @description:
 *
 * HandlerInterceptorAdapter 应用场景
 * 日志记录：可以记录请求信息的日志，以便进行信息监控、信息统计等。
 * 权限检查：如登陆检测，进入处理器检测是否登陆，如果没有直接返回到登陆页面。
 * 性能监控：典型的是慢日志。
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 概念模型：HandlerMethod
         *  SpringMVC 应用启动时会搜集并分析每个web控制器方法，从中提取对应的"<请求匹配条件，控制器方法>"映射关系，形成一个映射关系表
         *  保存在一个RequestMappingHandlerMapping bean中。然后在客户请求到达时，再使用RequestMappingHandlerMapping中的该映射
         *  关系表找到相应的控制器方法去处理该请求。
         *  在RequestMappingHandlerMapping中保存的每个"<请求匹配条件，控制器方法>"映射关系对儿中，"请求匹配条件"通过RequestMappingInfo
         *  包装和表示，而"控制器方法"则通过HandlerMethod来包装和表示。
         */
        if (handler instanceof HandlerMethod) {

        }
        return true;
    }
}
