package com.aladdin.apps.questionbank.data.bean;

/**
 * Created by AndySun on 2016/10/6.
 */
public class UserAnswer {
    private long answerId;
    private long orderId;
    private String answerContentJson; // answer json,example:
    private long score; // 用户当前分数
    private String wrongQuestIdsJson; // 错题的序号

    public UserAnswer() {
    }

    public UserAnswer(long answerId, long orderId, String answerContentJson, long score, String wrongQuestIdsJson) {
        this.answerId = answerId;
        this.orderId = orderId;
        this.answerContentJson = answerContentJson;
        this.score = score;
        this.wrongQuestIdsJson = wrongQuestIdsJson;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getAnswerContentJson() {
        return answerContentJson;
    }

    public void setAnswerContentJson(String answerContentJson) {
        this.answerContentJson = answerContentJson;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getWrongQuestIdsJson() {
        return wrongQuestIdsJson;
    }

    public void setWrongQuestIdsJson(String wrongQuestIdsJson) {
        this.wrongQuestIdsJson = wrongQuestIdsJson;
    }
}
