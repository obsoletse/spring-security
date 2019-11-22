package com.linbin.springSecurityJwt.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JWTAuthenticationEntryPoint //用来解决匿名用户访问无权限资源时的异常
 * @Author linbin
 * @Date 2019/11/14 16:53
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String reason = "未登录用户无权限访问资源!原因：" + authException.getMessage();
        response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
    }
}
