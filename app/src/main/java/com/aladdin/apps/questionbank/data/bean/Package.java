package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AndySun on 2016/10/6.
 * 比如：
 * 1) Java SSE - 第I段的套餐是JavaCore+SpringMVC+AngularJS
 */
public class Package implements Serializable {
    private long packageId; // 套餐ID
    private String packageName; // 套餐名称
    private long jobId; // 岗位的ID
    private String bankIdsJson;// 存放该套餐配置的一组bankID
    private Date createDate; // 创建时间
    private Date changeDate; // 套餐修改时间

    public Package() {
    }

    public Package(long packageId, String packageName,
                   long jobId, String bankIdsJson,
                   Date createDate, Date changeDate) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.jobId = jobId;
        this.bankIdsJson = bankIdsJson;
        this.createDate = createDate;
        this.changeDate = changeDate;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getBankIdsJson() {
        return bankIdsJson;
    }

    public void setBankIdsJson(String bankIdsJson) {
        this.bankIdsJson = bankIdsJson;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
