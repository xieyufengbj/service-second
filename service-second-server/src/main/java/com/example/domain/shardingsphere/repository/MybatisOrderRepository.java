package com.example.domain.shardingsphere.repository;

import com.example.domain.shardingsphere.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisOrderRepository extends CommonRepository<Order, Long> {
}
