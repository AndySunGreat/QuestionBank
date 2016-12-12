package com.aladdin.apps.questionbank.common.expandablelistview;

/**
 * Created by AndySun on 2016/12/9.
 */
public class HopperPositionsSubEntity {
    private String optSeq;
    private String optContent;
    private String requiredItem;
    private String requiredValue;
    private String requiredDegree;

    public HopperPositionsSubEntity() {
    }

    public HopperPositionsSubEntity(String optSeq, String optContent,
                                    String requiredItem, String requiredValue, String requiredDegree) {
        this.optSeq = optSeq;
        this.optContent = optContent;
        this.requiredItem = requiredItem;
        this.requiredValue = requiredValue;
        this.requiredDegree = requiredDegree;
    }

    public HopperPositionsSubEntity(String optSeq, String optContent) {
        this.optSeq = optSeq;
        this.optContent = optContent;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    public String getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(String requiredValue) {
        this.requiredValue = requiredValue;
    }

    public String getRequiredDegree() {
        return requiredDegree;
    }

    public void setRequiredDegree(String requiredDegree) {
        this.requiredDegree = requiredDegree;
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
