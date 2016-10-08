package com.aladdin.apps.questionbank.login.signup;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.data.bean.QuestionChooseItem;
import com.aladdin.apps.questionbank.question.bank.QuestionFeatureBankInteractor;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by AndySun on 2016/10/8.
 */
public class RegisterInteractorImpl implements RegisterInteractor {
    private BaseResultObject bro;
    @Override
    public void insertUserForSignup(final RegisterInteractor.OnCreatingUserFinishedListener listener, JSONObject jsonObject, Context context){
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.createNewUserUrl;
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

                Log.d("statusCode1",String.valueOf(statusCode));
                // called when response HTTP status is "200 OK"
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                listener.onFinished(bro);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode2",String.valueOf(statusCode));
                Log.e("MyApp", "Caught error", error);
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
