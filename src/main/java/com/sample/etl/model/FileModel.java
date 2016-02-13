package com.sample.etl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class FileModel {

    public String fileName;
    public List<Salesman> salesmen = new ArrayList<>();
    public List<Customer> customers = new ArrayList<>();
    public List<Sale> sales = new ArrayList<>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
