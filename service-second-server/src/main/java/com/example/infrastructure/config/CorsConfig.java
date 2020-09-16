package com.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Copyright (C)
 * FileName: CorsConfig
 *
 * @author: yufeng
 * @date: 2020/8/10 14:49
 * @description:
 */
@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();

        //允许跨域

        config.setAllowCredentials(true);

        //允许向该服务器提交请求的URI,*表示全部

        config.addAllowedOrigin("*");

        //允许访问的头信息,*表示全部

        config.addAllowedHeader("*");

        config.addExposedHeader("Content-Type,content-type,Content-Disposition,content-disposition");

        //允许的method

        config.addAllowedMethod("OPTIONS");

        config.addAllowedMethod("HEAD");

        config.addAllowedMethod("GET");

        config.addAllowedMethod("PUT");

        config.addAllowedMethod("POST");

        config.addAllowedMethod("DELETE");

        config.addAllowedMethod("PATCH");

        //Enabling CORS for the whole application

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }


}
