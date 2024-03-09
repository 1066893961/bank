package com.bank.manage.application.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/11/25 15:10
 */
public class InfoBean implements Serializable {

    /**
     * id : 1
     * userName : 李威振
     * password : 123456
     * studyNo : 003201
     * mobile : 13511187891
     * className : 七年级一班
     */

    private int id;
    private String userName;
    private String password;
    private String studyNo;
    private String mobile;
    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
