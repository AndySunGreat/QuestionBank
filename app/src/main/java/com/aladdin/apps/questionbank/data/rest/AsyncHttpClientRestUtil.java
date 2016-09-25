package com.aladdin.apps.questionbank.data.rest;

import android.content.Context;

import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/9/24.
 */
public class AsyncHttpClientRestUtil {
    private static final String BASE_URL = Constants.restfulEndpoints;
    private static String sessionId = null;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static PersistentCookieStore cookieStore ;
    static {
        //设置网络超时时间
        client.setTimeout(5000);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void get(Context context, String url, ResponseHandlerInterface responseHandler) {
        client.get(context, getAbsoluteUrl(url), responseHandler);

    }

    public static void get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);

    }

    public static void get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(context, getAbsoluteUrl(url), params, responseHandler);

    }

    public static void get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);

    }
    public static void post(String url,RequestParams params, ResponseHandlerInterface responseHandler){
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static AsyncHttpClient getClient(){
        return client;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        AsyncHttpClientRestUtil.sessionId = sessionId;
    }

    public static PersistentCookieStore getCookieStore() {
        return cookieStore;
    }

    public static void setCookieStore(PersistentCookieStore cookieStore) {
        AsyncHttpClientRestUtil.cookieStore = cookieStore;
        client.setCookieStore(cookieStore);
    }
}
