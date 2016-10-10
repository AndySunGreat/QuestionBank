package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;

/**
 * Created by AndySun on 2016/10/5.
 */
public class Options implements Serializable {
    private String optionSeq; // A,B
    private String optionContent;
    private String comments;
    private Boolean ifCorrect;

    public Options() {
    }

    public Options(String optionSeq, String optionContent, String comments, Boolean ifCorrect) {
        this.optionSeq = optionSeq;
        this.optionContent = optionContent;
        this.comments = comments;
        this.ifCorrect = ifCorrect;
    }

    public String getOptionSeq() {
        return optionSeq;
    }

    public void setOptionSeq(String optionSeq) {
        this.optionSeq = optionSeq;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIfCorrect() {
        return ifCorrect;
    }

    public void setIfCorrect(Boolean ifCorrect) {
        this.ifCorrect = ifCorrect;
    }
}
