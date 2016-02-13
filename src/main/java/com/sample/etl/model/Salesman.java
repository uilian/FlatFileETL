package com.sample.etl.model;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class Salesman {

    public String cpf;
    public String name;
    public Double salary;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
