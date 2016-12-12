package com.aladdin.apps.questionbank.base;

/**
 * Created by AndySun on 2016/12/9.
 */
public class BaseExpandListViewEntity {
    private String headVisibility;
    private String itemsVisibility;
    private String footVisibility;
    private String groupTitle;

    public BaseExpandListViewEntity() {
    }


    public BaseExpandListViewEntity(String headVisibility, String itemsVisibility, String footVisibility, String groupTitle) {
        this.headVisibility = headVisibility;
        this.itemsVisibility = itemsVisibility;
        this.footVisibility = footVisibility;
        this.groupTitle = groupTitle;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getHeadVisibility() {
        return headVisibility;
    }

    public void setHeadVisibility(String headVisibility) {
        this.headVisibility = headVisibility;
    }

    public String getItemsVisibility() {
        return itemsVisibility;
    }

    public void setItemsVisibility(String itemsVisibility) {
        this.itemsVisibility = itemsVisibility;
    }

    public String getFootVisibility() {
        return footVisibility;
    }

    public void setFootVisibility(String footVisibility) {
        this.footVisibility = footVisibility;
    }
}
