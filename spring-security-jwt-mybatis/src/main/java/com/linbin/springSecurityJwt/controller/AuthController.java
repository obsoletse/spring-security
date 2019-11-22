package com.linbin.springSecurityJwt.controller;

import com.linbin.springSecurityJwt.entity.User;
import com.linbin.springSecurityJwt.exception.MyException;
import com.linbin.springSecurityJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName AuthController
 * @Author linbin
 * @Date 2019/11/14 8:42
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register",produces = "application/json")
    public String registerUser(@RequestBody Map<String,String> registerUser) throws MyException {
        /*判断是否存在*/
        if (userService.findUserByName(registerUser.get("username"))!=null){
            throw new MyException("用户名已被注册!");
        }
        User user = new User();
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        userService.registerUser(user);
        /*获取插入的主键值id直接调用get方法。返回的是影响的行数*/
        return userService.findUserById(user.getId()).toString();
    }
}
