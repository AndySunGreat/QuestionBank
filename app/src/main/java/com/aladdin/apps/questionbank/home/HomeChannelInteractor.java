package com.aladdin.apps.questionbank.home;

import android.content.Context;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/19.
 */
public interface HomeChannelInteractor {
    interface  OnShowingOrdersFinishedListener{
        void onShowingOrdersFinished(List<Order> items);
        void onShowingOrdersFailure(BaseResultObject items);
    }
    interface  OnShowingPackagesFinishedListener{
        void onShowingPackagesFinished(List<Package> items);
        void onShowingPackagesFailure(BaseResultObject items);
    }

    void getOrderInfoByUserId(OnShowingOrdersFinishedListener listener, Map map, RequestParams params);

    void getPackageInfoById(OnShowingPackagesFinishedListener listener, Map map, RequestParams params);

}
