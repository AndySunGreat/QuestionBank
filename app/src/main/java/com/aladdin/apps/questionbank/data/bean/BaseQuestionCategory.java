package com.aladdin.apps.questionbank.data.bean;

/**
 * Created by AndySun on 2016/9/24.
 */
public class BaseQuestionCategory {
    private Long categoryId; // 技术范围ID JAVA Spring HTML/CSS Javascript
    private String categoryName;

    public BaseQuestionCategory(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
