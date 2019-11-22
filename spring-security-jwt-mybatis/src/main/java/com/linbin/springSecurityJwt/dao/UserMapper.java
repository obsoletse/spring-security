package com.linbin.springSecurityJwt.dao;

import com.linbin.springSecurityJwt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName UserMapper
 * @Author linbin
 * @Date 2019/11/13 15:39
 */
@Mapper
public interface UserMapper {
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
