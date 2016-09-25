package com.aladdin.apps.questionbank.data.bean;

import java.util.Date;

/**
 * Created by AndySun on 2016/9/24.
 */
public class OrderQuestionBank {
    private Long orderQBId;    // 用户订购ID,order_question_bank table
    private Long userId;       // 用户ID, base_user table
    private Long baseBankId;   // 题库ID, base_bank table
    private Date orderDatetime; // 订购时间
    private Date modifyDatetime; // 修改时间
    private String currentScore; // 当前成绩
    private String version; //题库版本

    /**
    * 用户回答答案:
    * [{
    *   "answerSeq": 0        -- 回答序号
    *   "questionId":"xxxx",
    *   "answerIfCorrect":"xxxx",  -- 回答是否正确
    *   "answerDate":"xxxx"  -- 回答时间
    * },
    * {
    *   "answerSeq": 0        -- 回答序号
    *   "questionId":"xxxx",
     *   "answerIfCorrect":"xxxx",  -- 回答是否正确
    *   "answerDate":"xxxx"  -- 回答时间
    * }]
    */
    private String answerJson;


    public OrderQuestionBank(Long orderQBId,
                             Long userId,
                             Long baseBankId,
                             Date orderDatetime,
                             Date modifyDatetime,
                             String currentScore,
                             String answerJson,
                             String version) {
        this.orderQBId = orderQBId;
        this.userId = userId;
        this.baseBankId = baseBankId;
        this.orderDatetime = orderDatetime;
        this.modifyDatetime = modifyDatetime;
        this.currentScore = currentScore;
        this.answerJson = answerJson;
        this.version = version;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public Long getOrderQBId() {
        return orderQBId;
    }

    public void setOrderQBId(Long orderQBId) {
        this.orderQBId = orderQBId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBaseBankId() {
        return baseBankId;
    }

    public void setBaseBankId(Long baseBankId) {
        this.baseBankId = baseBankId;
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

    public String getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(String currentScore) {
        this.currentScore = currentScore;
    }

    public String getAnswerJson() {
        return answerJson;
    }

    public void setAnswerJson(String answerJson) {
        this.answerJson = answerJson;
    }
}
