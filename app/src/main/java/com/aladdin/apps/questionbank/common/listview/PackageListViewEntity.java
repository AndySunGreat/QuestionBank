package com.aladdin.apps.questionbank.common.listview;

/**
 * Created by AndySun on 2016/10/10.
 */
public class PackageListViewEntity {
    private String packageId;
    private String bankIds;
    private String title;
    private String desc;
    private String time;
    private String phone;

    public PackageListViewEntity() {
    }

    public PackageListViewEntity(String title, String desc, String time, String phone) {
        this.title = title;

        this.desc = desc;
        this.time = time;
        this.phone = phone;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getBankIds() {
        return bankIds;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
    }

    public PackageListViewEntity(String packageId, String bankIds, String title, String desc, String time, String phone) {
        this.packageId = packageId;
        this.bankIds = bankIds;
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
