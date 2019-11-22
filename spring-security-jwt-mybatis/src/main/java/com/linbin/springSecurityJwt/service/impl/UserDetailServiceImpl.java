package com.linbin.springSecurityJwt.service.impl;

import com.linbin.springSecurityJwt.dao.UserMapper;
import com.linbin.springSecurityJwt.entity.JwtUser;
import com.linbin.springSecurityJwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserDetailServiceIml
 * @Author linbin
 * @Date 2019/11/14 10:35
 */
@Service //Component作用于类,@Bean作用于方法
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByName(username);
        return new JwtUser(user);
    }
}
