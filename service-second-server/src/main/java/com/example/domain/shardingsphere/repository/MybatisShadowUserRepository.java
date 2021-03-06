package com.example.domain.shardingsphere.repository;

import com.example.domain.shardingsphere.entity.ShadowUser;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MybatisShadowUserRepository  extends CommonRepository<ShadowUser, Long> {
    
    @Override
    default List<ShadowUser> selectAll() throws SQLException {
        List<ShadowUser> result = new ArrayList<>();
        result.addAll(selectAllByShadow(true));
        result.addAll(selectAllByShadow(false));
        return result;
    }
    
    List<ShadowUser> selectAllByShadow(boolean shadow) throws SQLException;
}
