package com.aladdin.apps.questionbank.data.bean;

import java.util.Date;

/**
 * Created by AndySun on 2016/9/24.
 */
public class BaseQuestionBank {
    private Long bankId;   // 题库ID, base_question_bank table
    private String bankName; // 题库名称 (Java Core, Java Thread)
    private Long categoryId;   // 技术范围ID JAVA Spring HTML/CSS Javascript
    private Date orderDatetime; // 订购时间
    private Date modifyDatetime; // 修改时间
    private String author; // 作者

    public BaseQuestionBank(Long bankId, String bankName, Long categoryId, Date orderDatetime, Date modifyDatetime, String author) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.categoryId = categoryId;
        this.orderDatetime = orderDatetime;
        this.modifyDatetime = modifyDatetime;
        this.author = author;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Date orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public Date getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
