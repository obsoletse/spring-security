package com.linbin.service;

import com.linbin.domain.Permission;
import com.linbin.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Author linbin
 * @Date 2019/11/7 15:48
 */
public interface UserService {
    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 通过用户名查询权限
     * @param username
     * @return
     */
    List<Permission> findPermissionByUsername(String username);
}
