package com.sample.etl.model;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class Customer {

    public String cnpj;
    public String name;
    public String businessArea;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}
