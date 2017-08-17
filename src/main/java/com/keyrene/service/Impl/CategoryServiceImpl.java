package com.keyrene.service.Impl;

import com.keyrene.mapper.CategoryMapper;
import com.keyrene.po.Category;
import com.keyrene.po.CategoryExample;
import com.keyrene.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dell on 2017/7/30.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> selectAllCategory(){
        List<Category> datas;
        CategoryExample categoryExample = new CategoryExample();

        datas = categoryMapper.selectByExample(categoryExample);

        return datas;
    }
}
