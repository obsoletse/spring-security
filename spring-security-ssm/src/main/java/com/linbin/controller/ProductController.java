package com.linbin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ProductController
 * @Author linbin
 * @Date 2019/10/23 14:09
 */
@Controller
public class ProductController {

    @RequestMapping("/index")
    public String index(Model model ){
        //获取登录的用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null){
            if (principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails)principal;
                String username = userDetails.getUsername();
                model.addAttribute("username",username);
            }
        }
        return "index";
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
