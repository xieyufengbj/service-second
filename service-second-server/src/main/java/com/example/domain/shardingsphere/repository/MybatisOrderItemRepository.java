package com.example.domain.shardingsphere.repository;

import com.example.domain.shardingsphere.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisOrderItemRepository extends CommonRepository<OrderItem, Long> {
}
