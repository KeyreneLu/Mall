package com.keyrene.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keyrene.domain.EasyUiDataForGrid;
import com.keyrene.mapper.ProductMapper;
import com.keyrene.po.Product;
import com.keyrene.po.ProductExample;
import com.keyrene.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2017/7/26.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> findHotProductList() {
        List<Product> products = productMapper.findHotProductList();
        return products;
    }

    @Override
    public List<Product> findNewProductList() {
        List<Product> products = productMapper.findNewProductList();
        return products;
    }

    @Override
    public EasyUiDataForGrid selectAllProductByCid(String cid,int currentPage) {

        PageHelper.startPage(currentPage,12);

        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(cid);
        List<Product> datas = productMapper.selectByExample(example);
        //取分页结果
        PageInfo<Product> info = new PageInfo<>(datas);

        //返回tree控件填充数据
        EasyUiDataForGrid grid = new EasyUiDataForGrid();
        grid.setPage(info.getPages());
        grid.setTotal(info.getTotal());
        grid.setRows(datas);
        grid.setCurrentPage(info.getPageNum());
        grid.setCurrentCount(info.getPageSize());
//        System.out.print("number========"+grid.toString());
        return grid;
    }

    @Override
    public Product selectProductByPid(String pid) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);

        List<Product> datas = productMapper.selectByExample(example);

        return datas.get(0);
    }
}
