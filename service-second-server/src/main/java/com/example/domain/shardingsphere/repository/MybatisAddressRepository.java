package com.example.domain.shardingsphere.repository;

import com.example.domain.shardingsphere.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisAddressRepository extends CommonRepository<Address, Long> {
}
