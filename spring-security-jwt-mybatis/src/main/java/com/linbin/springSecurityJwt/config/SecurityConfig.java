package com.linbin.springSecurityJwt.config;

import com.linbin.springSecurityJwt.exception.handler.JWTAccessDeniedHandler;
import com.linbin.springSecurityJwt.exception.handler.JWTAuthenticationEntryPoint;
import com.linbin.springSecurityJwt.filter.JWTAuthenticationFilter;
import com.linbin.springSecurityJwt.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfig
 * @Author linbin
 * @Date 2019/11/14 10:30
 */
@EnableWebSecurity //开启Web安全
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启spring-security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")  //@Qualifier 和 @Resource 都是指定注入时的具体实现类,@Qualifier直接加名称,@Resource (name = "...")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder();}

    /**
     * 添加用户认证信息到AuthenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 配置资源拦截和权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests() //请求授权
                .antMatchers(HttpMethod.DELETE,"/tasks/**").hasAnyRole("ADMIN") //对应url需要的权限
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/tasks/**").authenticated()
                .anyRequest().permitAll()//其他放行
                .and()
                //添加自定义的认证授权处理
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不需要session
                .and()
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler()); //无权限时处理

    }
}
