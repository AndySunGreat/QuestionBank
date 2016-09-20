package com.aladdin.apps.questionbank.util;
import com.loopj.android.http.*;
/**
 * Created by AndySun on 2016/9/19.
 */
public class RestClientUtil {
    private static final String BASE_URL = Constants.restfulEndpoints;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
