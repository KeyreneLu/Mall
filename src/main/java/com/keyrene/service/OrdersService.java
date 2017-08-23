package com.keyrene.service;

import com.keyrene.po.Orders;

/**
 * Created by Dell on 2017/8/20.
 */
public interface OrdersService {

    boolean insertOrderByCart(Orders orders);

    boolean updateAddressByOid(Orders orders);
}
