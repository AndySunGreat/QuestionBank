package com.aladdin.apps.questionbank.packages;

import android.view.View;

import org.json.JSONObject;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface PackagesPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();

}
