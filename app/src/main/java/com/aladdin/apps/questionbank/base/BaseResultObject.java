package com.aladdin.apps.questionbank.base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class BaseResultObject {
    private int resultStateCode;
    private String resultMsg;
    private Map resultDataMap;
    private JSONArray resultJSONArray;
    private JSONObject resultJSONObject;

    public BaseResultObject() {
    }

    public BaseResultObject(int resultStateCode, String resultMsg,
                            Map resultDataMap, JSONArray resultJSONArray,
                            JSONObject resultJSONObject) {
        this.resultStateCode = resultStateCode;
        this.resultMsg = resultMsg;
        this.resultDataMap = resultDataMap;
        this.resultJSONArray = resultJSONArray;
        this.resultJSONObject = resultJSONObject;
    }

    public Map getResultDataMap() {
        return resultDataMap;
    }

    public void setResultDataMap(Map resultDataMap) {
        this.resultDataMap = resultDataMap;
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
