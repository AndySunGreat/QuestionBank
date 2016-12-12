package com.aladdin.apps.questionbank.common.expandablelistview;

import java.util.List;

/**
 * Created by AndySun on 2016/12/13.
 */
public class HopperPositionsGroupEntity {
    private String groupId;
    private String groupName;
    private List<HopperPositionsEntity> hopperPositionsEntityList;

    public HopperPositionsGroupEntity() {
    }

    public HopperPositionsGroupEntity(String groupId, String groupName, List<HopperPositionsEntity> hopperPositionsEntityList) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.hopperPositionsEntityList = hopperPositionsEntityList;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<HopperPositionsEntity> getHopperPositionsEntityList() {
        return hopperPositionsEntityList;
    }

    public void setHopperPositionsEntityList(List<HopperPositionsEntity> hopperPositionsEntityList) {
        this.hopperPositionsEntityList = hopperPositionsEntityList;
    }
}
