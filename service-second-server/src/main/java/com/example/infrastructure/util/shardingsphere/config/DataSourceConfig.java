package com.example.infrastructure.util.shardingsphere.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * Copyright (C), 2020
 * FileName: DataSourceConfig
 *
 * @author: yufeng
 * @date: 2020/9/17 14:32
 * @description:
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({Master0Property.class, Master1Property.class, Slave0Property.class, Slave1Property.class})
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.example.domain", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {

    @Bean(name = "master0")
    public DataSource createMaster0(Master0Property master0Property) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(master0Property.getType());
        dataSource.setUrl(master0Property.getUrl());
        dataSource.setUsername(master0Property.getUsername());
        dataSource.setPassword(master0Property.getPassword());
        dataSource.setDriverClassName(master0Property.getDriverClassName());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    @Bean(name = "master1")
    public DataSource createMaster1(Master1Property master1Property) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(master1Property.getType());
        dataSource.setUrl(master1Property.getUrl());
        dataSource.setUsername(master1Property.getUsername());
        dataSource.setPassword(master1Property.getPassword());
        dataSource.setDriverClassName(master1Property.getDriverClassName());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    @Bean(name = "slave0")
    public DataSource createSlave0(Slave0Property slave0Property) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(slave0Property.getType());
        dataSource.setUrl(slave0Property.getUrl());
        dataSource.setUsername(slave0Property.getUsername());
        dataSource.setPassword(slave0Property.getPassword());
        dataSource.setDriverClassName(slave0Property.getDriverClassName());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    @Bean(name = "slave1")
    public DataSource createSlave1(Slave1Property slave1Property) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(slave1Property.getType());
        dataSource.setUrl(slave1Property.getUrl());
        dataSource.setUsername(slave1Property.getUsername());
        dataSource.setPassword(slave1Property.getPassword());
        dataSource.setDriverClassName(slave1Property.getDriverClassName());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("master0") DataSource master0, @Qualifier("master1") DataSource master1,
                                 @Qualifier("slave0") DataSource slave0, @Qualifier("slave1") DataSource slave1) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master0", master0);
        dataSourceMap.put("master1", master1);
        dataSourceMap.put("slave0", slave0);
        dataSourceMap.put("slave1", slave1);

        List<String> slave0List = new ArrayList<>();
        slave0List.add("slave0");
        List<String> slave1List = new ArrayList<>();
        slave1List.add("slave1");

        // 打开shardingsphere sql日志
        Properties properties = new Properties();
        properties.setProperty("sql.show", Boolean.TRUE.toString());

        // 主从策略
        LoadBalanceStrategyConfiguration loadBalanceStrategyConfiguration = new LoadBalanceStrategyConfiguration("round_robin");
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration0 = new MasterSlaveRuleConfiguration("master0", "master0", slave0List, loadBalanceStrategyConfiguration);
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration1 = new MasterSlaveRuleConfiguration("master1", "master1", slave1List, loadBalanceStrategyConfiguration);

        // 配置分片规则 分库分表 读写分离
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfiguration0);
        shardingRuleConfiguration.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfiguration1);

        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration());

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, properties);
        return dataSource;
    }

    TableRuleConfiguration tableRuleConfiguration() {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("", "");

        return tableRuleConfiguration;
    }

    @Bean
    public PlatformTransactionManager txManage(@Qualifier("dataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations();

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
