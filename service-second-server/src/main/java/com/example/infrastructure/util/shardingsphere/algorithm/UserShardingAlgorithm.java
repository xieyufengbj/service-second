package com.example.infrastructure.util.shardingsphere.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * Copyright (C), 2020, 上海昌投网络科技有限公司
 * FileName: UserShardingAlgorithm
 *
 * @author: yufeng
 * @date: 2020/9/22 15:23
 * @description:
 */
public class UserShardingAlgorithm {

    public static final DatabaseShardingAlgorithm databaseShardingAlgorithm = new DatabaseShardingAlgorithm();

    public static final TableShardingAlgorithm tableShardingAlgorithm = new TableShardingAlgorithm();

    static class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

        @Override
        public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {
            for (String database : databaseNames) {
                if (database.endsWith(String.valueOf(shardingValue.getValue() % 2))) {
                    return database;
                }
            }
            return "";
        }
    }

    static class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

        @Override
        public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> shardingValue) {
            for (String table : tableNames) {
                if (table.endsWith(String.valueOf(shardingValue.getValue() % 2))) {
                    return table;
                }
            }
            return "";
        }
    }
}
