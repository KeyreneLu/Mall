package com.keyrene.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 2017/8/2.
 */
public class Cart {

    private Map<String,CartItem> item = new HashMap<>();

    private double total;
    public Map<String, CartItem> getItem() {
        return item;
    }

    public void setItem(Map<String, CartItem> item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
