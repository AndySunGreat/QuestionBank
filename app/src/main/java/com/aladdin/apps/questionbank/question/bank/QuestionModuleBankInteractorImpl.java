package com.aladdin.apps.questionbank.question.bank;

import android.util.Log;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.data.rest.AsyncHttpClientRestUtil;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.cache.Resource;

/**
 * Created by AndySun on 2016/9/24.
 */
public class QuestionModuleBankInteractorImpl implements QuestionModuleBankInteractor{

    private BaseResultObject bro;
    @Override
    public void getOrderQuestionBankByUserId(final OnOrderQuestionBankFinishedListener listener,RequestParams params){
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.getOrderQuestionBankUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("statusCode","200");
                // called when response HTTP status is "200 OK"
                // progress.hide();
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                bro.setResultJSONArray(response);
                // 存储数组变量
                List<ChannelRow> objects = new ArrayList<ChannelRow>();
                for (int i = 0; i < response.length(); i++) {
                    ChannelRow cr = new ChannelRow();
                    try {
                        // 获取具体的一个JSONObject对象
                        JSONObject obj = response.getJSONObject(i);
                        cr.setRowIconImgName("ic_friends");
                        //cr.setRowIcon(R.id.rowLogo);
                        cr.setRowName(obj.getString("bankName"));

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    objects.add(cr);
                }
                listener.onFinished(objects);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content,
                                  Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode","4XX");
                // Hide Progress Dialog
                //progress.hide();
                String resultErrorMsg = "";
                // When Http response code is '404'
                if (statusCode == 404) {
                    resultErrorMsg="Requested resource not found";
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    resultErrorMsg= "Something went wrong at server end";
                }
                // When Http response code other than 404, 500
                else {
                    resultErrorMsg= "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]";
                }
                bro.setResultStateCode(statusCode);
                bro.setResultMsg(resultErrorMsg);
                bro.setResultJSONArray(null);
                listener.onFailure(bro);
            }
        });
    }


}
