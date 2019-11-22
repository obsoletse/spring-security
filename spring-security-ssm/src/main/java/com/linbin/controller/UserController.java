package com.linbin.controller;

import com.linbin.domain.Permission;
import com.linbin.domain.User;
import com.linbin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Author linbin
 * @Date 2019/11/7 16:00
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{username}")
    public User getUser(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @RequestMapping("getAuthorization/{username}")
    public List<Permission> getAuthorization(@PathVariable String username){
        return userService.findPermissionByUsername(username);
    }
}
