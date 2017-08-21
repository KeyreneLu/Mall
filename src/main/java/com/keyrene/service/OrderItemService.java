package com.keyrene.service;

import com.keyrene.po.Orderitem;

/**
 * Created by Dell on 2017/8/20.
 */
public interface OrderItemService {

    boolean insertOrderItemByCart(Orderitem orderitem);
}
