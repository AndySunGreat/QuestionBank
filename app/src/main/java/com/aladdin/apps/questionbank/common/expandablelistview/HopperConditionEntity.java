package com.aladdin.apps.questionbank.common.expandablelistview;

import com.aladdin.apps.questionbank.base.BaseExpandListViewEntity;

import java.util.List;

/**
 * Created by AndySun on 2016/12/9.
 */
public class HopperConditionEntity extends BaseExpandListViewEntity{
    private Long conditionId;
    private String conditionTitle;

    private List<HopperConditionSubEntity> items;

    public HopperConditionEntity() {
        super();
    }

    public HopperConditionEntity(String headVisibility, String itemsVisibility, String footVisibility,
                                 String groupTitle, Long conditionId, String conditionTitle, List<HopperConditionSubEntity> items) {
        super(headVisibility, itemsVisibility, footVisibility, groupTitle);
        this.conditionId = conditionId;
        this.conditionTitle = conditionTitle;
        this.items = items;
    }

    public HopperConditionEntity(Long conditionId, String conditionTitle, List<HopperConditionSubEntity> items) {
        this.conditionId = conditionId;
        this.conditionTitle = conditionTitle;
        this.items = items;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionTitle() {
        return conditionTitle;
    }

    public void setConditionTitle(String conditionTitle) {
        this.conditionTitle = conditionTitle;
    }



    public List<HopperConditionSubEntity> getItems() {
        return items;
    }

    public void setItems(List<HopperConditionSubEntity> items) {
        this.items = items;
    }
}
