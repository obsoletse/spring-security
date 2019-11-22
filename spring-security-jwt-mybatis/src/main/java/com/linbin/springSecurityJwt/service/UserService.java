package com.linbin.springSecurityJwt.service;

import com.linbin.springSecurityJwt.entity.User;

/**
 * @ClassName UserService
 * @Author linbin
 * @Date 2019/11/13 15:35
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void registerUser(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 查询用户
     * @param username
     * @return
     */
    User findUserByName(String username);
}
