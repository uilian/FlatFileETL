package com.sample.etl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class Sale {

    public Integer id;
    public List<Item> items = new ArrayList<>();
    public String salesmanName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }
}
