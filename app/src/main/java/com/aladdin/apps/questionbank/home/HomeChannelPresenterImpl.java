package com.aladdin.apps.questionbank.home;

import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.answers.AnswersInteractor;
import com.aladdin.apps.questionbank.answers.AnswersPresenter;
import com.aladdin.apps.questionbank.answers.AnswersView;
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
public class HomeChannelPresenterImpl implements HomeChannelPresenter,
        HomeChannelInteractor.OnShowingOrdersFinishedListener{

    private HomeChannelView homeChannelView;
    private HomeChannelInteractor homeChannelInteractor;
    private Map map;
    public HomeChannelPresenterImpl(HomeChannelView homeChannelView,
                                    HomeChannelInteractor homeChannelInteractor) {
        this.homeChannelView = homeChannelView;
        this.homeChannelInteractor = homeChannelInteractor;
    }

    @Override
    public void onResume() {
        if (homeChannelView != null) {
            //homeChannelView.showTitleBar();
            //homeChannelView.showProgress();
            //map = homeChannelView.getFilterParams();
        }
        RequestParams params = new RequestParams();
        homeChannelInteractor.getOrderInfoByUserId(this,map,params);
    }

    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onFinished(List<Order> items) {
        if (homeChannelView != null) {
            homeChannelView.setItems(items);
            //homeChannelView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (homeChannelView != null) {
            homeChannelView.setItemsError(items);
            //homeChannelView.hideProgress();
        }
    }
}
