package com.aladdin.apps.questionbank.data.bean;

/**
 * Created by AndySun on 2016/9/24.
 */
public class BaseQuestionType {
    private Long questionTypeId;
    private String questionTypeName;  // 单选,多选,判断,问答

    public BaseQuestionType(Long questionTypeId, String questionTypeName) {
        this.questionTypeId = questionTypeId;
        this.questionTypeName = questionTypeName;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }
}
