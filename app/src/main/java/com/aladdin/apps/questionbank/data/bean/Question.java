package com.aladdin.apps.questionbank.data.bean;

import com.aladdin.apps.questionbank.common.expandablelistview.QuestionSubEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AndySun on 2016/10/11.
 */
public class Question implements Serializable {
    private long questionId;
    private long bankId;
    private String questContent;
    private String questType; // 1-单选 2-多选
    private String questOptionsJson;
    private String correctAnswer;
    private String changeDate;
    private List<QuestionSubEntity> questOptions;
    private String correctIndexes;

    public Question() {
    }

    public Question(long questionId, long bankId, String questContent,
                    String questType, String questOptionsJson,
                    String correctAnswer, String changeDate,
                    List<QuestionSubEntity> questOptions, String correctIndexes) {

        this.questionId = questionId;
        this.bankId = bankId;
        this.questContent = questContent;
        this.questType = questType;
        this.questOptionsJson = questOptionsJson;
        this.correctAnswer = correctAnswer;
        this.changeDate = changeDate;
        this.questOptions = questOptions;
        this.correctIndexes = correctIndexes;
    }

    public String getCorrectIndexes() {
        return correctIndexes;
    }

    public void setCorrectIndexes(String correctIndexes) {
        this.correctIndexes = correctIndexes;
    }

    public List<QuestionSubEntity> getQuestOptions() {
        return questOptions;
    }

    public void setQuestOptions(List<QuestionSubEntity> questOptions) {
        this.questOptions = questOptions;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getQuestContent() {
        return questContent;
    }

    public void setQuestContent(String questContent) {
        this.questContent = questContent;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestOptionsJson() {
        return questOptionsJson;
    }

    public void setQuestOptionsJson(String questOptionsJson) {
        this.questOptionsJson = questOptionsJson;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }
}
