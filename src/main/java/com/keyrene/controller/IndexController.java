package com.keyrene.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2017/7/20.
 */
@Controller
public class IndexController extends BaseController{

    @RequestMapping("/")
    public String welcome(){
        return "redirect:/index";
    }
}
