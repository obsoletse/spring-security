package com.linbin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName MainControkker
 * @Author linbin
 * @Date 2019/10/23 16:36
 */
@RestController
public class MainController {

    @RequestMapping("/userLogin")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/error")
    public ModelAndView error(){
        return new ModelAndView("error");
    }
}
