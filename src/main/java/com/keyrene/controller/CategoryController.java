package com.keyrene.controller;

import com.keyrene.po.Category;
import com.keyrene.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 2017/7/30.
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping("/category/list")
    @ResponseBody
    public Map selectAllCategory(){
        Map<String ,Object> map = new HashMap<>();

        List<Category> datas = service.selectAllCategory();

        if (datas.size()==0||datas == null){
            map.put("flag",false);
        }else {
            map.put("flag",true);
            map.put("data",datas);
        }

        return map;
    }
}
