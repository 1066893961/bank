package com.bank.manage.application.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/12/22 11:47
 */
public class TransactionDetailListBean implements Serializable {

    /**
     * msg : Success
     * current : 1
     * size : 20
     * total : 10
     * list : [{"transDateTime":"2020-12-18 20:30:12","orderNo":"202012184257917985","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"**杰(136******57)","amount":"2.00","serviceFee":"0.00","status":"成功","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 18:51:47","orderNo":"202012184246048959","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"**杰(136******57)","amount":"1.00","serviceFee":"0.00","status":"成功","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 18:48:44","orderNo":"202012184245511231","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 17:40:04","orderNo":"202012184234214094","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 17:03:25","orderNo":"202012184226532172","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 17:02:13","orderNo":"202012184226300524","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 16:57:24","orderNo":"202012184225343273","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 16:56:20","orderNo":"202012184225109193","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 16:50:15","orderNo":"202012184223922711","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"},{"transDateTime":"2020-12-18 16:48:13","orderNo":"202012184223509796","typeDesc":"转账","subTypeDesc":null,"fundDesc":"支付宝余额","account":"j*","amount":"1.00","serviceFee":"0.00","status":"失败","instructionId":null,"memo":"支付宝代发专户 ****010781919552"}]
     */

    private String msg;
    private String current;
    private String size;
    private String total;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * transDateTime : 2020-12-18 20:30:12
         * orderNo : 202012184257917985
         * typeDesc : 转账
         * subTypeDesc : null
         * fundDesc : 支付宝余额
         * account : **杰(136******57)
         * amount : 2.00
         * serviceFee : 0.00
         * status : 成功
         * instructionId : null
         * memo : 支付宝代发专户 ****010781919552
         */

        private String transDateTime;
        private String orderNo;
        private String typeDesc;
        private String subTypeDesc;
        private String fundDesc;
        private String account;
        private String amount;
        private String serviceFee;
        private String status;
        private Object instructionId;
        private String memo;

        public String getTransDateTime() {
            return transDateTime;
        }

        public void setTransDateTime(String transDateTime) {
            this.transDateTime = transDateTime;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

        public String getSubTypeDesc() {
            return subTypeDesc;
        }

        public void setSubTypeDesc(String subTypeDesc) {
            this.subTypeDesc = subTypeDesc;
        }

        public String getFundDesc() {
            return fundDesc;
        }

        public void setFundDesc(String fundDesc) {
            this.fundDesc = fundDesc;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getServiceFee() {
            return serviceFee;
        }

        public void setServiceFee(String serviceFee) {
            this.serviceFee = serviceFee;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getInstructionId() {
            return instructionId;
        }

        public void setInstructionId(Object instructionId) {
            this.instructionId = instructionId;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
