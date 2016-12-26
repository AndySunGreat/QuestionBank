package com.aladdin.apps.questionbank.common.listview;

/**
 * Created by AndySun on 2016/10/10.
 */
public class PositionListViewEntity {
    private String positionId;
    private String companyId;
    private String positionName;
    private String requiredJson;
    private String salary;
    private String experience;
    private String city;
    private String changeData;

    public PositionListViewEntity() {
    }

    public PositionListViewEntity(String positionId, String companyId,
                                 String positionName, String requiredJson,
                                 String salary, String experience,
                                 String city, String changeData) {
        this.positionId = positionId;
        this.companyId = companyId;
        this.positionName = positionName;
        this.requiredJson = requiredJson;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
        this.changeData = changeData;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRequiredJson() {
        return requiredJson;
    }

    public void setRequiredJson(String requiredJson) {
        this.requiredJson = requiredJson;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChangeData() {
        return changeData;
    }

    public void setChangeData(String changeData) {
        this.changeData = changeData;
    }
}
