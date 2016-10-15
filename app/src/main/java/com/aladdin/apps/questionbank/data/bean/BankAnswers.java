package com.aladdin.apps.questionbank.data.bean;

import java.util.Date;

/**
 * Created by AndySun on 2016/10/6.
 */
public class BankAnswers {
    private long answerId;
    private long bankId;
    private long score;
    private long orderId;
    private String wrongQuestIds;
    private Date createDate;

    public BankAnswers() {
    }

    public BankAnswers(long answerId, long bankId, long score, long orderId, String wrongQuestIds, Date createDate) {
        this.answerId = answerId;
        this.bankId = bankId;
        this.score = score;
        this.orderId = orderId;
        this.wrongQuestIds = wrongQuestIds;
        this.createDate = createDate;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getWrongQuestIds() {
        return wrongQuestIds;
    }

    public void setWrongQuestIds(String wrongQuestIds) {
        this.wrongQuestIds = wrongQuestIds;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
