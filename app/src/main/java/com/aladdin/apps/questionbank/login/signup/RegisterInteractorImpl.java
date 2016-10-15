package com.aladdin.apps.questionbank.login.signup;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
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
        String strUserId = "1";
/*        try {
            strUserId = jsonObject.get("userId").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
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
        //保存cookie，自动保存到了shareprefercece
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        BasicClientCookie newCookie = new BasicClientCookie("userId", strUserId);
        newCookie.setVersion(1);
        newCookie.setDomain("mydomain.com");
        newCookie.setPath("/");
        myCookieStore.addCookie(newCookie);
        client.setCookieStore(myCookieStore);
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.post(context,url,entity,"application/json",new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response){

                Log.d("statusCode",String.valueOf(statusCode));
                Log.d("Register","注册成功,cookie");
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
