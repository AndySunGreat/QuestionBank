package com.aladdin.apps.questionbank.common.expandablelistview;

import com.aladdin.apps.questionbank.base.BaseExpandListViewEntity;

import java.util.List;

/**
 * Created by AndySun on 2016/12/12.
 */
public class HopperPositionsEntity extends BaseExpandListViewEntity {
    private long positionId;
    private String positionName;
    private String requiredJson;
    private List<HopperPositionsSubEntity> hopperPositionsSubEntityList;
    private String salary;
    private long experience;
    private String city;

    public HopperPositionsEntity(){

    }

    public HopperPositionsEntity(long positionId, String positionName, String requiredJson,
                                 List<HopperPositionsSubEntity> hopperPositionsSubEntityList,
                                 String salary, long experience, String city) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.requiredJson = requiredJson;
        this.hopperPositionsSubEntityList = hopperPositionsSubEntityList;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public HopperPositionsEntity(String headVisibility, String itemsVisibility, String footVisibility, String groupTitle, long positionId, String positionName, String requiredJson, List<HopperPositionsSubEntity> hopperPositionsSubEntityList, String salary, long experience, String city) {
        super(headVisibility, itemsVisibility, footVisibility, groupTitle);
        this.positionId = positionId;
        this.positionName = positionName;
        this.requiredJson = requiredJson;
        this.hopperPositionsSubEntityList = hopperPositionsSubEntityList;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
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

    public List<HopperPositionsSubEntity> getHopperPositionsSubEntityList() {
        return hopperPositionsSubEntityList;
    }

    public void setHopperPositionsSubEntityList(List<HopperPositionsSubEntity> hopperPositionsSubEntityList) {
        this.hopperPositionsSubEntityList = hopperPositionsSubEntityList;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
