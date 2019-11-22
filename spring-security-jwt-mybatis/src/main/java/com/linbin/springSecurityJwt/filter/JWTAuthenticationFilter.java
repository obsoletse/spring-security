package com.linbin.springSecurityJwt.filter;

import com.linbin.springSecurityJwt.entity.JwtUser;
import com.linbin.springSecurityJwt.model.LoginUser;
import com.linbin.springSecurityJwt.utils.JwtTokenUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @ClassName JWTAuthenticationFilter
 * @Author linbin
 * @Date 2019/11/14 11:15
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //设置记住我
    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>(); //ThreadLocal<Integer>是一个线程内部存储类，对数据存储只有在当前线程才可以取到
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");//设置自定义登录路径
    }

    /**
     * 自定义尝试认证
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /*1.从输入流中获取用户信息*/
        try{
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(),LoginUser.class);
            rememberMe.set(loginUser.getRememberMe());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword()));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 认证成功，生成token并且设置权限
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("JwtUser : " + jwtUser);

        boolean isRemember = rememberMe.get() == 1;

        /*设置权限*/
        String role = null;
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }

        /*生成token*/
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(),role,isRemember);

        /*返回Token*/
        response.setHeader("Authorization",JwtTokenUtils.TOKEN_PREFIX + token);
    }

    /**
     * 认证失败,抛出异常
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
