package com.webmvc.mywebmvc.model;

import java.io.Serializable;

public class Payroll implements Serializable {

    private Integer id;

    private Integer contract_id;
    private Integer absent_total;
    private Integer salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getAbsent_total() {
        return absent_total;
    }

    public void setAbsent_total(Integer absent_total) {
        this.absent_total = absent_total;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
