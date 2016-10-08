package com.aladdin.apps.questionbank.login.signup;

import android.view.View;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface RegisterPresenter {
    void onResume();

    void onClickd(View v,JSONObject jsonObject);

    void onDestroy();

}
