package com.aladdin.apps.questionbank.base;

/**
 * Created by AndySun on 2016/12/9.
 */
public class BaseExpandListViewEntity {
    private String headVisibility;
    private String itemsVisibility;
    private String footVisibility;

    public BaseExpandListViewEntity() {
    }

    public BaseExpandListViewEntity(String headVisibility, String itemsVisibility, String footVisibility) {
        this.headVisibility = headVisibility;
        this.itemsVisibility = itemsVisibility;
        this.footVisibility = footVisibility;
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
