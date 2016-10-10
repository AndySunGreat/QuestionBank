package com.aladdin.apps.questionbank.data.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AndySun on 2016/10/2.
 */
public class User implements Serializable {
    private long id;
    private String username;
    private String password;
    private String jobName;
    private Date changeDate;

    public User() {
    }

    public User(long id, String username, String password, String jobName, Date changeDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.jobName = jobName;
        this.changeDate = changeDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
