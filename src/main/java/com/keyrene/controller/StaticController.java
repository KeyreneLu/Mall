package com.keyrene.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2017/7/22.
 */
@Controller
public class StaticController extends BaseController{

    @RequestMapping("/account/register.html")
    public String toRegister(){

        return "register";
    }

//    @RequestMapping("/good/product_info.html")
//    public String toGoodInfo(){
//
//        return "product_info";
//    }

    @RequestMapping("/account/login.html")
    public String toLogin(){

        return "login";
    }

    @RequestMapping("/cart")
    public String toCart(){

        return "cart";
    }

}
