package com.aladdin.apps.questionbank.common.expandablelistview;

import java.util.List;

/**
 * Created by AndySun on 2016/10/12.
 */
public class QuestionOrder {
    private String questTitle ;
    private List<QuestionItem> items ;
    private String correctAnswer;
    private String answerResult;
    private String questionType;
    private String correctPostions;
    private String headVisibility;
    private String itemsVisibility;
    private String footVisibility;
    private  int orderTest;
    public static final int VISIBLE = 0;
    //private double totalPrices ;

    public final int getOrderTest() {
        return orderTest;
    }

    public final void setOrderTest( int orderTest) {
        this.orderTest = orderTest;
    }

    public QuestionOrder() {
    }
    public QuestionOrder(String questTitle, List<QuestionItem> items, String correctAnswer) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
    }

    public QuestionOrder(String questTitle, List<QuestionItem> items, String correctAnswer, String answerResult) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
        this.answerResult = answerResult;
    }

    public QuestionOrder(String questTitle, List<QuestionItem> items,
                         String correctAnswer, String answerResult, String questionType) {
        this.questTitle = questTitle;
        this.items = items;
        this.correctAnswer = correctAnswer;
        this.answerResult = answerResult;
        this.questionType = questionType;
    }

    public QuestionOrder(String questTitle, List<QuestionItem> items,
                         String correctAnswer, String answerResult,
                         String questionType, String correctPostions,
                         String headVisibility, String itemsVisibility, String footVisibility) {
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



    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public List<QuestionItem> getItems() {
        return items;
    }

    public void setItems(List<QuestionItem> items) {
        this.items = items;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }
}
