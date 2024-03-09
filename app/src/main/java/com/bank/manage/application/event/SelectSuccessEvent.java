package com.bank.manage.application.event;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2019/9/25 14:50
 */
public class SelectSuccessEvent {

    private String mNum;

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public SelectSuccessEvent(String num) {
        this.mNum = num;
    }
}

