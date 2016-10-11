package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by AndySun on 2016/10/5.
 * Industry Type: IT行业; 每个行业会对应有一张题库表
 */
public class ITQuestion extends Question implements Serializable {

    private List<Options> questOptions; // 问题选项

    public ITQuestion(long questionId, long bankId, String questContent, String questType, String questOptionsJson, String correctAnswer, String changeDate, List<Options> questOptions) {
        super(questionId, bankId, questContent, questType, questOptionsJson, correctAnswer, changeDate);
        this.questOptions = questOptions;
    }

    public List<Options> getQuestOptions() {
        return questOptions;
    }

    public void setQuestOptions(List<Options> questOptions) {
        this.questOptions = questOptions;
    }
}
