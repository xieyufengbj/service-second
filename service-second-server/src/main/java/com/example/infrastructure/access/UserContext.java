package com.example.infrastructure.access;

/**
 * Copyright (C)
 * FileName: UserContext
 *
 * @author: yufeng
 * @date: 2020/7/29 09:42
 * @description:
 */
public class UserContext {

    private static ThreadLocal<SecondUser> userHolder = new ThreadLocal<>();

    public static void setUser(SecondUser user) {

        userHolder.set(user);
    }

    public static SecondUser getUser() {

        return userHolder.get();
    }
}
