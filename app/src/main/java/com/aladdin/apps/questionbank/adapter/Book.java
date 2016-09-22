package com.aladdin.apps.questionbank.adapter;

/**
 * Created by AndySun on 2016/9/23.
 */
public class Book {
    private String bName;
    private String bAuthor;

    public Book() {
    }

    public Book(String bName, String bAuthor) {
        this.bName = bName;
        this.bAuthor = bAuthor;
    }

    public String getbName() {
        return bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }
}