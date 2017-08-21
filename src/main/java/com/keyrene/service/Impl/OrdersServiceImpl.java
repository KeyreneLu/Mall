package com.keyrene.service.Impl;

import com.keyrene.mapper.OrdersMapper;
import com.keyrene.po.Orders;
import com.keyrene.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dell on 2017/8/20.
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper mOrdersMapper;


    @Override
    public boolean insertOrderByCart(Orders orders) {

        int id = mOrdersMapper.insert(orders);

        if (id <0) {
            return false;
        }else {
            return true;
        }
    }
}
