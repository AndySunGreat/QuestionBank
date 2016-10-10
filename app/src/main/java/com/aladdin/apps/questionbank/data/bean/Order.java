package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AndySun on 2016/10/5.
 * 用户订单表
 */
public class Order implements Serializable {
    private long orderId;
    private long userId;
    private String orderType; // 1:bank(订购面试题库)
    private String orderStatus; // 当前order状态
    private Date changeDate;
    private String bankId; // 以分号;分隔的多个bankId,或是其它ids.一次用户可以定购多个题库
    private String answerId; // 用户记录用户学习题库的答案,一个answerId对应一个bankId
    private String packageId; // 套餐ID

    public Order() {
    }

    public Order(long orderId, long userId, String orderType, String orderStatus,
                 Date changeDate, String bankId, String answerId,String packageId) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.changeDate = changeDate;
        this.bankId = bankId;  // 一个题库一个订单号
        this.answerId = answerId;
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
