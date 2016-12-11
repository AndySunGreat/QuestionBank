package com.aladdin.apps.questionbank.common.expandablelistview;

import com.aladdin.apps.questionbank.base.BaseExpandListViewEntity;

import java.util.List;

/**
 * Created by AndySun on 2016/12/9.
 */
public class HopperConditionEntity extends BaseExpandListViewEntity{
    private Long conditionId;
    private String conditionTitle;
    private String groupTitle;

    private List<HopperConditionSubEntity> items;

    public HopperConditionEntity() {
        super();
    }

    public HopperConditionEntity(String headVisibility, String itemsVisibility, String footVisibility) {
        super(headVisibility, itemsVisibility, footVisibility);
    }
    public HopperConditionEntity(String headVisibility, String itemsVisibility, String footVisibility,List<HopperConditionSubEntity> items) {
        super(headVisibility, itemsVisibility, footVisibility);
        this.items = items;
    }

    public HopperConditionEntity(String headVisibility, String itemsVisibility, String footVisibility,
                                 Long conditionId, String conditionTitle, List<HopperConditionSubEntity> items) {
        super(headVisibility, itemsVisibility, footVisibility);
        this.conditionId = conditionId;
        this.conditionTitle = conditionTitle;
        this.items = items;
    }

    public HopperConditionEntity(String headVisibility, String itemsVisibility, String footVisibility, Long conditionId,
                                 String conditionTitle, String groupTitle, List<HopperConditionSubEntity> items) {
        super(headVisibility, itemsVisibility, footVisibility);
        this.conditionId = conditionId;
        this.conditionTitle = conditionTitle;
        this.groupTitle = groupTitle;
        this.items = items;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
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
