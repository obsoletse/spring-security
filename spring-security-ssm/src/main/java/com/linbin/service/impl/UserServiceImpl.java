package com.linbin.service.impl;

import com.linbin.domain.Permission;
import com.linbin.domain.User;
import com.linbin.mapper.UserMapper;
import com.linbin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author linbin
 * @Date 2019/11/7 15:49
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public List<Permission> findPermissionByUsername(String username) {
        return userMapper.findPermissionByUsername(username);
    }
}
