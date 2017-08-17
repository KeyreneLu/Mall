package com.keyrene.service;

import com.keyrene.domain.EasyUiDataForGrid;
import com.keyrene.po.Product;

import java.util.List;

/**
 * Created by DELL on 2017/7/26.
 */
public interface ProductService {

    List<Product> findHotProductList();

    List<Product> findNewProductList();

    EasyUiDataForGrid selectAllProductByCid(String cid,int currentPage);

    Product selectProductByPid(String pid);
}
