package com.keyrene.controller;

import com.keyrene.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2017/7/22.
 */
public class BaseController {

    @ExceptionHandler(MyException.class)
    public String handleException(Exception e, HttpServletRequest request){
        request.setAttribute("error",e.getMessage()+"");
        return "exception";
    }
}
