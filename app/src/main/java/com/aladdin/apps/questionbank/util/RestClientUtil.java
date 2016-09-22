package com.aladdin.apps.questionbank.util;
import android.content.Context;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/9/19.
 */
public class RestClientUtil {
    private static String sessionId = null;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static PersistentCookieStore cookieStore ;
    static {
        //设置网络超时时间
        client.setTimeout(5000);
    }

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);

    }

    public static void get(Context context, String url, ResponseHandlerInterface responseHandler) {
        client.get(context, url, responseHandler);

    }

    public static void get(String url,RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(url, params, responseHandler);

    }

    public static void get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(context, url, params, responseHandler);

    }

    public static void get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(context, url, headers, params, responseHandler);

    }
    public static void post(String url,RequestParams params, ResponseHandlerInterface responseHandler){
        client.post(url, params, responseHandler);
    }
    public static AsyncHttpClient getClient(){
        return client;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        RestClientUtil.sessionId = sessionId;
    }

    public static PersistentCookieStore getCookieStore() {
        return cookieStore;
    }

    public static void setCookieStore(PersistentCookieStore cookieStore) {
        RestClientUtil.cookieStore = cookieStore;
        client.setCookieStore(cookieStore);
    }

}