package com.aladdin.apps.questionbank.answers;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by AndySun on 2016/10/8.
 */
public class AnswersInteractorImpl implements AnswersInteractor {

    private BaseResultObject bro;
    @Override
    public void doNextBank(final OnNextBankFinishedListener listener, JSONObject jsonObjectParam, Context context) {
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.crudOrderPartialUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        //StringEntity entity = null;
        ByteArrayEntity entity = null;
        JSONObject jsonObjectData = new JSONObject();
        try {
            jsonObjectData.put("userId",jsonObjectParam.getString("userId"));
            jsonObjectData.put("orderType","1");  // 1.bank
            jsonObjectData.put("orderStatus","NEW");
            jsonObjectData.put("bankId",jsonObjectParam.getString("newBankId"));
            jsonObjectData.put("packageId",jsonObjectParam.getString("packageId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            // entity = new StringEntity(jsonObject.toString());
            entity = new ByteArrayEntity(jsonObjectData.toString().getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.post(context,url,entity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject){

                Log.d("statusCode",String.valueOf(statusCode));
                Log.d("createOrderByBankId","创建Order成功!");
                // called when response HTTP status is "200 OK"
                /*bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");*/
                Order newOrder = new Order();
                try {
                    newOrder.setOrderId(jsonObject.getLong("orderId"));
                    Log.d("newOrderId:",String.valueOf(jsonObject.getLong("orderId")));
                    newOrder.setUserId(jsonObject.getLong("userId"));
                    newOrder.setBankId(jsonObject.getString("bankId"));
                    newOrder.setOrderStatus(jsonObject.getString("orderStatus"));
                    newOrder.setOrderType(jsonObject.getString("orderType"));
                    newOrder.setPackageId(jsonObject.getString("packageId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onCreateOrderFinished(newOrder);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,String content, Throwable error) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode2",String.valueOf(statusCode));
                Log.d("createOrderByBankId","创建Order失败!");
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
                listener.onCreateOrderFailure(bro);
            }
        });
    }

    /**
     *             jsonObject.put("score",String.valueOf(getIntent().getLongExtra("score",1L)));
     jsonObject.put("wrongQuestIds",getIntent().getStringExtra("wrongQuestIds"));
     jsonObject.put("oldBankId",String.valueOf(getIntent().getLongExtra("bankId",1L)));
     jsonObject.put("answerId",String.valueOf(getIntent().getLongExtra("answerId",1L)));
     jsonObject.put("orderId",getIntent().getStringExtra("orderId"));
     jsonObject.put("packageId",getIntent().getStringExtra("packageId"));
     jsonObject.put("bankIds",getIntent().getStringExtra("bankIds"));
     * @param listener
     * @param jsonObjectParam
     * @param context
     */

    @Override
    public void doAnswerAgain(final OnAnswerAgainFinishedListener listener,final JSONObject jsonObjectParam, Context context) {
        bro = new BaseResultObject();
            String orderId = "";
            AsyncHttpClient client = new AsyncHttpClient();
            try {
                orderId = jsonObjectParam.getString("orderId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = Constants.restfulEndpoints + Constants.updateOrderStatusUrl + "/"+ orderId;
            Log.d("url:",url);
            //StringEntity entity = null;
            ByteArrayEntity entity = null;
            try {
                Log.d("doAnswerAgainUpdParam",jsonObjectParam.toString());
                // entity = new StringEntity(jsonObject.toString());
                entity = new ByteArrayEntity(jsonObjectParam.toString().getBytes("UTF-8"));
            }catch (UnsupportedEncodingException e1){
                e1.printStackTrace();
            }
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
            client.put(context,url,entity,"application/json",new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject){

                    Log.d("statusCode",String.valueOf(statusCode));
                    Log.d("createOrderByBankId","更新order状态为Again成功!");
                    // called when response HTTP status is "200 OK"
                    bro.setResultStateCode(statusCode);
                    Order order = new Order();
                    try {
                        bro.setResultMsg(jsonObject.getString("msg"));
                        //order.setOrderId();
                        order.setOrderId(jsonObject.getLong("orderId"));
                        order.setAnswerId(jsonObject.getString("answerId"));
                        order.setOrderStatus(jsonObject.getString("orderStatus"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listener.onAnswerAgainFinished(order);
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String content, Throwable error) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.d("statusCode2",String.valueOf(statusCode));
                    Log.d("createOrderByBankId","更新order状态为Again失败!");
                    error.printStackTrace();
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
                    listener.onAnswerAgainFailure(bro);
                }
            });
     }
}
