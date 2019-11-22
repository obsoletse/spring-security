package com.linbin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ProductController
 * @Author linbin
 * @Date 2019/10/23 14:09
 */
@RestController
public class ProductController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping("/product/delete")
    public ModelAndView delete(){
        ModelAndView mv = new ModelAndView("product/delete");
        return mv;
    }
    @RequestMapping("/product/add")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("product/add");
        return mv;
    }
    @RequestMapping("/product/update")
    public ModelAndView update(){
        ModelAndView mv = new ModelAndView("product/update");
        return mv;
    }
    @RequestMapping("/product/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("product/list");
        return mv;
    }
}
