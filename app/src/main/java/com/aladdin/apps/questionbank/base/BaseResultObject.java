package com.aladdin.apps.questionbank.base;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by AndySun on 2016/9/24.
 */
public class BaseResultObject {
    private int resultStateCode;
    private String resultMsg;
    private JSONArray resultJSONArray;
    private JSONObject resultJSONObject;

    public BaseResultObject() {
    }

    public BaseResultObject(int resultStateCode, String resultMsg, JSONObject resultJSONObject) {
        this.resultStateCode = resultStateCode;
        this.resultMsg = resultMsg;
        this.resultJSONObject = resultJSONObject;
    }

    public BaseResultObject(int resultStateCode, String resultMsg, JSONArray resultJSONArray) {
        this.resultStateCode = resultStateCode;
        this.resultMsg = resultMsg;
        this.resultJSONArray = resultJSONArray;
    }

    public BaseResultObject(int resultStateCode, String resultMsg, JSONArray resultJSONArray, JSONObject resultJSONObject) {
        this.resultStateCode = resultStateCode;
        this.resultMsg = resultMsg;
        this.resultJSONArray = resultJSONArray;
        this.resultJSONObject = resultJSONObject;
    }

    public int getResultStateCode() {
        return resultStateCode;
    }

    public void setResultStateCode(int resultStateCode) {
        this.resultStateCode = resultStateCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public JSONArray getResultJSONArray() {
        return resultJSONArray;
    }

    public void setResultJSONArray(JSONArray resultJSONArray) {
        this.resultJSONArray = resultJSONArray;
    }

    public JSONObject getResultJSONObject() {
        return resultJSONObject;
    }

    public void setResultJSONObject(JSONObject resultJSONObject) {
        this.resultJSONObject = resultJSONObject;
    }
}
