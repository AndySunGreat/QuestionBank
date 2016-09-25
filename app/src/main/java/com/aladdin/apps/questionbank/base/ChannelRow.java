package com.aladdin.apps.questionbank.base;

/**
 * Created by AndySun on 2016/9/23.
 */
public class ChannelRow {
    private int rowIcon;
    private String rowName;
    private int rowBadgeIcon;
    private String rowIconImgName;


    public ChannelRow() {
    }

    public ChannelRow(int rowIcon, String rowName) {
        this.rowName = rowName;
        this.rowIcon = rowIcon;
    }

    public ChannelRow(int rowIcon, String rowName, int rowBadgeIcon) {
        this.rowIcon = rowIcon;
        this.rowName = rowName;
        this.rowBadgeIcon = rowBadgeIcon;
    }

    public String getRowIconImgName() {
        return rowIconImgName;
    }

    public void setRowIconImgName(String rowIconImgName) {
        this.rowIconImgName = rowIconImgName;
    }

    public int getRowIcon() {
        return rowIcon;
    }

    public void setRowIcon(int rowIcon) {
        this.rowIcon = rowIcon;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public int getRowBadgeIcon() {
        return rowBadgeIcon;
    }

    public void setRowBadgeIcon(int rowBadgeIcon) {
        this.rowBadgeIcon = rowBadgeIcon;
    }
}
