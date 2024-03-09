package com.bank.manage.application.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/2 18:06
 */
public class CommonListBean implements Serializable {

    /**
     * id : 1
     * userId : 2
     * bankName : 中国银行
     * bankCardNo : 628123456
     * bankCardAddr : null
     * money : null
     * createAt : 1617357891988
     */

    private int id;
    private int userId;
    private String bankName;
    private String bankCardNo;
    private String targetBankCardNo;
    private String primaryBankCardNo;
    private String transferMoney;
    private Object bankCardAddr;
    private double money;
    private long createAt;

    public String getTargetBankCardNo() {
        return targetBankCardNo;
    }

    public void setTargetBankCardNo(String targetBankCardNo) {
        this.targetBankCardNo = targetBankCardNo;
    }

    public String getPrimaryBankCardNo() {
        return primaryBankCardNo;
    }

    public void setPrimaryBankCardNo(String primaryBankCardNo) {
        this.primaryBankCardNo = primaryBankCardNo;
    }

    public String getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(String transferMoney) {
        this.transferMoney = transferMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Object getBankCardAddr() {
        return bankCardAddr;
    }

    public void setBankCardAddr(Object bankCardAddr) {
        this.bankCardAddr = bankCardAddr;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
