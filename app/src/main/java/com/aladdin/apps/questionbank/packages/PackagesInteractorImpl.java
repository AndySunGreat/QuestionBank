package com.aladdin.apps.questionbank.packages;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.login.signup.RegisterInteractor;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by AndySun on 2016/10/8.
 */
public class PackagesInteractorImpl  implements PackagesInteractor {
    private BaseResultObject bro;
    private List<Package> packageList;
    private JSONObject obj;
    private Package autoPackage;
    private SimpleDateFormat sdf;
    private String strCreateDate;
    private String strChangeDate;
    private java.util.Date date;
    @Override
    public void getAutoPackagesByJobId(final OnShowingPackagesFinishedListener listener, Map map, RequestParams params){
        bro = new BaseResultObject();
        String jobId = map.get("jobId").toString();
        String url = Constants.restfulEndpoints + Constants.getAutoPkgsByJobIdOneUrl + jobId + Constants.getAutoPkgsByJobIdTwoUrl;
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
                Log.d("getPkgsInfo","获得Package信息成功!");
                packageList = new ArrayList<Package>();
                // 循环遍历auoRecommendPackages
                for (int i = 0; i < response.length(); i++) {
                    autoPackage = new Package();
                    try {
                        obj = response.getJSONObject(i);
                        autoPackage.setPackageId(obj.getLong("packageId"));
                        autoPackage.setBankIdsJson(obj.getString("bankIdsJson"));
                        autoPackage.setJobId(obj.getLong("jobId"));
                        autoPackage.setPackageName(obj.getString("packageName"));
                        sdf =new SimpleDateFormat("yyyy-MM-dd");
                        strCreateDate = obj.getString("createDate");
                        strChangeDate = obj.getString("changeDate");
                        if(strChangeDate!=null) {
                            autoPackage.setCreateDate((java.util.Date) sdf.parse(strCreateDate));
                        }
                        if(strChangeDate!=null){
                            autoPackage.setChangeDate((java.util.Date)sdf.parse(strChangeDate));
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }catch (ParseException e2){
                        e2.printStackTrace();
                    }
                    packageList.add(autoPackage);
                }
                listener.onFinished(packageList);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content,
                                  Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode","4XX");
                Log.d("getPkgsInfo","获得Package信息失败!");
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

    // insert first order record by calling /api/me/order[POST] when to click 'submit' in package choosing page.
    @Override
    public void generateOrderForPackages(final PackagesInteractor.OnCreatingOrderFinishedListener listener, JSONObject jsonObject, Context context){
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.crudOrderPartialUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        //StringEntity entity = null;
        ByteArrayEntity entity = null;
        try {
            // entity = new StringEntity(jsonObject.toString());
            entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.post(context,url,entity,"application/json",new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response){

                Log.d("statusCode",String.valueOf(statusCode));
                Log.d("createOrderByBankId","创建Order成功!");
                // called when response HTTP status is "200 OK"
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                listener.onFinished(bro);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
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
                listener.onFailure(bro);
            }
        });
    }

}
