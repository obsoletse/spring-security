package com.linbin.springSecurityJwt.exception;

/**
 * @ClassName UserExistsException
 * @Author linbin
 * @Date 2019/11/14 10:00
 */
public class MyException extends Exception {
    public MyException(String message){
        super(message);
    }
}
