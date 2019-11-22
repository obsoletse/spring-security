package com.linbin.springSecurityJwt.exception.handler;

import com.linbin.springSecurityJwt.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserExistsExceptionHandler
 * @Author linbin
 * @Date 2019/11/14 10:08
 */
@RestControllerAdvice //处理controller抛出的异常
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object>  MyExceptionHandler(MyException e){
        Map<String,Object> result = new HashMap<>();
        result.put("code",404);
        result.put("message",e.getMessage());
        return result;
    }
}
