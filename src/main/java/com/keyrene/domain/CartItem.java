package com.keyrene.domain;

import com.keyrene.po.Product;

/**
 * Created by Dell on 2017/8/2.
 */
public class CartItem {

    private Product product;
    private int buyNum;
    private double cost;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public CartItem(Product product, int buyNum, double cost) {
        this.product = product;
        this.buyNum = buyNum;
        this.cost = cost;
    }

    public CartItem() {
    }
}
