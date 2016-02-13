package com.sample.etl.model;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class Report {
    public String fileName;
    public Long numberOfCustomers;
    public Long numberOfSalesman;
    public Integer idMostExpensiveSale;
    public String worstSalesmanEver;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(Long numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public Long getNumberOfSalesman() {
        return numberOfSalesman;
    }

    public void setNumberOfSalesman(Long numberOfSalesman) {
        this.numberOfSalesman = numberOfSalesman;
    }

    public Integer getIdMostExpensiveSale() {
        return idMostExpensiveSale;
    }

    public void setIdMostExpensiveSale(Integer idMostExpensiveSale) {
        this.idMostExpensiveSale = idMostExpensiveSale;
    }

    public String getWorstSalesmanEver() {
        return worstSalesmanEver;
    }

    public void setWorstSalesmanEver(String worstSalesmanEver) {
        this.worstSalesmanEver = worstSalesmanEver;
    }
}
