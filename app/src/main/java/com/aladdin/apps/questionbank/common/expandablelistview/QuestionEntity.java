package com.aladdin.apps.questionbank.common.expandablelistview;

import java.util.List;

/**
 * Created by AndySun on 2016/10/12.
 */
public class QuestionEntity {
    private Long questionId;
    private String questTitle ;
    private List<QuestionSubEntity> items ;
    private String correctAnswer;
    private String answerResult;
    private String questionType;
    private String correctPostions;
    private String headVisibility;
    private String itemsVisibility;
    private String footVisibility;
    private  int orderTest;
    //private double totalPrices ;



    public QuestionEntity() {
    }
    public QuestionEntity(String questTitle, List<QuestionSubEntity> items, String correctAnswer) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
    }

    public QuestionEntity(String questTitle, List<QuestionSubEntity> items, String correctAnswer, String answerResult) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
        this.answerResult = answerResult;
    }

    public QuestionEntity(String questTitle, List<QuestionSubEntity> items,
                          String correctAnswer, String answerResult, String questionType) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
        this.answerResult = answerResult;
        this.questionType = questionType;
    }

    public QuestionEntity(Long questionId, String questTitle, List<QuestionSubEntity> items,
                          String correctAnswer, String answerResult,
                          String questionType, String correctPostions,
                          String headVisibility, String itemsVisibility, String footVisibility) {
        this.questionId = questionId;
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
        this.answerResult = answerResult;
        this.questionType = questionType;
        this.correctPostions = correctPostions;
        this.headVisibility = headVisibility;
        this.itemsVisibility = itemsVisibility;
        this.footVisibility = footVisibility;
    }



    public final int getOrderTest() {
        return orderTest;
    }

    public final void setOrderTest( int orderTest) {
        this.orderTest = orderTest;
    }

    public String getHeadVisibility() {
        return headVisibility;
    }

    public void setHeadVisibility(String headVisibility) {
        this.headVisibility = headVisibility;
    }

    public String getItemsVisibility() {
        return itemsVisibility;
    }

    public void setItemsVisibility(String itemsVisibility) {
        this.itemsVisibility = itemsVisibility;
    }

    public String getCorrectPostions() {
        return correctPostions;
    }

    public String getFootVisibility() {
        return footVisibility;
    }

    public void setFootVisibility(String footVisibility) {
        this.footVisibility = footVisibility;
    }

    public void setCorrectPostions(String correctPostions) {
        this.correctPostions = correctPostions;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public List<QuestionSubEntity> getItems() {
        return items;
    }

    public void setItems(List<QuestionSubEntity> items) {
        this.items = items;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }
}
