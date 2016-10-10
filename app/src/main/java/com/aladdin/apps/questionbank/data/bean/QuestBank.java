package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AndySun on 2016/10/4.
 * Industry Type: IT行业; 每个行业会对应有一张题库表
 */
public class QuestBank implements Serializable {
    private long bankId;
    private String bankName; // JAVA CORE
    private String qbCategory; // 1:前端开发  2.后端开发 3.移动开发  4.测试  5.运维  6.DBA 7.硬件开发 8.高端职位
    private String industryType; // IT
    private String qbType; // JAVA,PHP,C#,C++,.NET,Python,Perl
    private String version;
    private Date changeDate;

    public QuestBank() {
    }



    public QuestBank(long bankId, String bankName,
                     String qbCategory, String industryType,
                     String qbType, String version,
                     Date changeDate) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.qbCategory = qbCategory;
        this.industryType = industryType;
        this.qbType = qbType;
        this.version = version;
        this.changeDate = changeDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public long getBankId() {
        return bankId;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getQbCategory() {
        return qbCategory;
    }

    public void setQbCategory(String qbCategory) {
        this.qbCategory = qbCategory;
    }

    public String getQbType() {
        return qbType;
    }

    public void setQbType(String qbType) {
        this.qbType = qbType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
