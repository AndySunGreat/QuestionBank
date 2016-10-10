package com.aladdin.apps.questionbank.component.entity;

/**
 * Created by AndySun on 2016/10/10.
 */
public class ListViewEntity {
    private String title;
    private String desc;
    private String time;
    private String phone;

    public ListViewEntity() {
    }

    public ListViewEntity(String phone, String title, String desc, String time) {
        this.phone = phone;
        this.title = title;
        this.desc = desc;
        this.time = time;
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
