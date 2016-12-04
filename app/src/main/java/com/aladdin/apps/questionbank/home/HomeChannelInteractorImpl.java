package com.aladdin.apps.questionbank.home;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.packages.PackagesInteractor;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/10/19.
 */
public class HomeChannelInteractorImpl implements HomeChannelInteractor{
    private BaseResultObject bro;
    private Order orders;
    private List<Order> orderList;
    private JSONObject obj;
    private SimpleDateFormat sdf;
    private String strChangeDate;
    private java.util.Date date;
    @Override
    public void getOrderInfoByUserId(final HomeChannelInteractor.OnShowingOrdersFinishedListener listener, Map map, RequestParams params){
        bro = new BaseResultObject();
        String userId = map.get("userId").toString();
        String url = Constants.restfulEndpoints + Constants.getOrderInfoByUserIdUrl + "?userId="+ userId;
        Log.d("Pkg-url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                super.onSuccess(statusCode, headers, response);
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                bro.setResultJSONArray(response);
                Log.d("Get Orders Info","获得Orders信息成功!");
                orderList = new ArrayList<Order>();
                // 循环遍历auoRecommendPackages
                for (int i = 0; i < response.length(); i++) {
                    orders = new Order();
                    try {
                        obj = response.getJSONObject(i);
                        orders.setOrderId(obj.getLong("orderId"));
                        orders.setPackageId(String.valueOf(obj.getLong("packageId")));
                        orders.setOrderStatus(obj.getString("orderStatus"));
                        orders.setOrderType(obj.getString("orderType"));
                        orders.setAnswerId(obj.getString("answerId"));
                        orders.setBankId(obj.getString("bankId"));
                        orders.setUserId(obj.getLong("userId"));
                        sdf =new SimpleDateFormat("yyyy-MM-dd");
                        //strCreateDate = obj.getString("createDate");
                        strChangeDate = obj.getString("changeDate");
                        if(strChangeDate!=null){
                            orders.setChangeDate((java.sql.Date)sdf.parse(strChangeDate));
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }catch (ParseException e2){
                        e2.printStackTrace();
                    }
                    orderList.add(orders);
                }
                listener.onFinished(orderList);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content,
                                  Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode","4XX");
                Log.d("getOrdersInfo","获得Orders信息失败!");
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
