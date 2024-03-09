package com.bank.manage.application.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/12/2 18:29
 */
public class PayBean implements Serializable {
    private long employeesId;
    private double salaryAmount;

    public long getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(long employeesId) {
        this.employeesId = employeesId;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}
