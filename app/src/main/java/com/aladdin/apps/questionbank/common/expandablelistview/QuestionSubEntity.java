package com.aladdin.apps.questionbank.common.expandablelistview;

/**
 * Created by AndySun on 2016/10/12.
 */
public class QuestionSubEntity {
    private String optSeq;
    private String optContent;
    public QuestionSubEntity() {
    }

    public QuestionSubEntity(String optSeq, String optContent) {
        this.optSeq = optSeq;
        this.optContent = optContent;
    }


    public String getOptSeq() {
        return optSeq;
    }

    public void setOptSeq(String optSeq) {
        this.optSeq = optSeq;
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent;
    }
}
