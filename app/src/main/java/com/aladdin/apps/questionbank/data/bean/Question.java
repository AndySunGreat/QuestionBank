package com.aladdin.apps.questionbank.data.bean;

/**
 * Created by AndySun on 2016/9/24.
 */
public class Question {
    private Long questionId;
    private Long questionCategoryId;
    private Long questionBankId;
    private Long questionTypeId;
    private String questionSubject; //题目
    private String answerJson;
    private String questionComments;

    public Question(Long questionId, Long questionCategoryId, Long questionBankId, Long questionTypeId, String questionSubject, String answerJson, String questionComments) {
        this.questionId = questionId;
        this.questionCategoryId = questionCategoryId;
        this.questionBankId = questionBankId;
        this.questionTypeId = questionTypeId;
        this.questionSubject = questionSubject;
        this.answerJson = answerJson;
        this.questionComments = questionComments;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Long questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public Long getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(Long questionBankId) {
        this.questionBankId = questionBankId;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    public String getAnswerJson() {
        return answerJson;
    }

    public void setAnswerJson(String answerJson) {
        this.answerJson = answerJson;
    }

    public String getQuestionComments() {
        return questionComments;
    }

    public void setQuestionComments(String questionComments) {
        this.questionComments = questionComments;
    }
}
