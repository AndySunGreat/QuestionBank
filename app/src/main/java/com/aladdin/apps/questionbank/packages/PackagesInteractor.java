package com.aladdin.apps.questionbank.packages;

import android.content.Context;
import android.content.Intent;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface PackagesInteractor {

    // Loading auto recommend packages based on jobId
    interface OnShowingPackagesFinishedListener{
        void onFinished(List<Package> items);
        void onFailure(BaseResultObject items);
    }
    interface OnCreatingOrderFinishedListener{
        void onFinished(BaseResultObject items);
        void onFailure(BaseResultObject items);
    }
    // AHC calling for getting auto recommend packages based on JobId
    void getAutoPackagesByJobId(OnShowingPackagesFinishedListener listener, Map map, RequestParams params);
    // insert first order record by calling /api/me/order[POST] when to click 'submit' in package choosing page.
    void generateOrderForPackages(OnCreatingOrderFinishedListener listener, JSONObject jsonObject, Context context);
}
