package com.example.infrastructure.util.shardingsphere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Copyright (C)
 * FileName: ShardingJDBCConfig
 *
 * @author: yufeng
 * @date: 2020/8/27 08:40
 * @description:
 */
@Configuration
@EnableTransactionManagement
public class ShardingSphereTransactionConfiguration {

    /**
     * Create platform transaction manager bean.
     * @param dataSource
     * @return platform transaction manager
     */
    @Bean
    public PlatformTransactionManager txManage(final DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Create JDBC template bean.
     * @param dataSource
     * @return JDBC template bean
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }
}
