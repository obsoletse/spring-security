package com.linbin.springSecurityJwt.service.impl;

import com.linbin.springSecurityJwt.dao.UserMapper;
import com.linbin.springSecurityJwt.entity.User;
import com.linbin.springSecurityJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author linbin
 * @Date 2019/11/13 15:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        userMapper.registerUser(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
