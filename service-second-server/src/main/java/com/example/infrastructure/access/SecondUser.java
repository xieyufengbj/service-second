package com.example.infrastructure.access;

import lombok.Data;

import java.util.Date;

/**
 * Copyright (C)
 * FileName: SecondUser
 *
 * @author: yufeng
 * @date: 2020/8/6 08:57
 * @description:
 */
@Data
public class SecondUser {
    // 主键id
    private Long id;
    // 用户名
    private String nickname;
    // 密码
    private String password;
    // 加盐因子
    private String salt;
    private String head;
    // 注册日期
    private Date registerDate;
    // 最后登陆时间
    private Date lastLoginDate;
    // 登陆次数
    private Integer loginCount;
}
