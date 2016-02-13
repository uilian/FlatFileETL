package com.sample.etl.model;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class Item {

    public Integer id;
    public Double quantity;
    public Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
