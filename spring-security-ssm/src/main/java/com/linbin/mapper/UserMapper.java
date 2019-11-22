package com.linbin.mapper;

import com.linbin.domain.Permission;
import com.linbin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author linbin
 * @Date 2019/11/7 14:44
 */
@Mapper
public interface UserMapper {
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
