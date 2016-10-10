package com.aladdin.apps.questionbank.component.entity;

/**
 * Created by AndySun on 2016/10/10.
 */
public class SpinnerData {

    private String value = "";
    private String text = "";

    public SpinnerData() {
        value = "";
        text = "";
    }

    public SpinnerData(String _value, String _text) {
        value = _value;
        text = _text;
    }

    @Override
    public String toString() {

        return text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
