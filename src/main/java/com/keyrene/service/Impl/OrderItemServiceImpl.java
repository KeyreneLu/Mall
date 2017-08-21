package com.keyrene.service.Impl;

import com.keyrene.mapper.OrderitemMapper;
import com.keyrene.po.Orderitem;
import com.keyrene.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dell on 2017/8/20.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderitemMapper mOrderitemMapper;

    @Override
    public boolean insertOrderItemByCart(Orderitem orderitem) {
        int id = mOrderitemMapper.insertSelective(orderitem);
        if (id<0){
            return false;
        }else {
            return true;
        }
    }
}
