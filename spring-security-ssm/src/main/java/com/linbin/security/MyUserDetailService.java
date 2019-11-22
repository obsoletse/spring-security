package com.linbin.security;

import com.linbin.domain.Permission;
import com.linbin.domain.User;
import com.linbin.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyUserDetailService
 * @Author linbin
 * @Date 2019/11/8 10:22
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    private Logger logger = Logger.getLogger(MyUserDetailService.class);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);

        if (user != null) {
            /*获取用户权限*/
            List<Permission> permissionList = userMapper.findPermissionByUsername(username);
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
            for (Permission permission : permissionList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermTag());
                grantedAuthorityList.add(authority);
            }

            /*传入User*/
            user.setAuthorities(grantedAuthorityList);

            logger.info("当前用户: " + user);
        }
        return user;
    }
}
