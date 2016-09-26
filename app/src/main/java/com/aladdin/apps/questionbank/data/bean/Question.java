package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public class Question implements Serializable {
    private Long questionId;
    private Long questionCategoryId;
    private String questionCategoryName;
    private Long questionBankId;
    private String questionBankName;
    private Long questionTypeId;
    private String questionTypeName;
    private String questionSubject; //题目
    private List<QuestionChooseItem> answerItemsList;
    private String questionComments;
    private String answers;


    public Question() {
    }

    public Question(Long questionId,
                    Long questionCategoryId,
                    String questionCategoryName,
                    Long questionBankId,
                    String questionBankName,
                    Long questionTypeId,
                    String questionTypeName,
                    String questionSubject,
                    List<QuestionChooseItem> answerItemsList,
                    String questionComments,
                    String answers) {
        this.questionId = questionId;
        this.questionCategoryId = questionCategoryId;
        this.questionCategoryName = questionCategoryName;
        this.questionBankId = questionBankId;
        this.questionBankName = questionBankName;
        this.questionTypeId = questionTypeId;
        this.questionTypeName = questionTypeName;
        this.questionSubject = questionSubject;
        this.answerItemsList = answerItemsList;
        this.questionComments = questionComments;
        this.answers = answers;
    }


    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
    public List<QuestionChooseItem> getAnswerItemsList() {
        return answerItemsList;
    }

    public void setAnswerItemsList(List<QuestionChooseItem> answerItemsList) {
        this.answerItemsList = answerItemsList;
    }

    public String getQuestionCategoryName() {
        return questionCategoryName;
    }

    public void setQuestionCategoryName(String questionCategoryName) {
        this.questionCategoryName = questionCategoryName;
    }

    public String getQuestionBankName() {
        return questionBankName;
    }

    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
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

    public String getQuestionComments() {
        return questionComments;
    }

    public void setQuestionComments(String questionComments) {
        this.questionComments = questionComments;
    }
}
