package com.aladdin.apps.questionbank.login.signup;

import android.content.Context;

import com.aladdin.apps.questionbank.base.BaseResultObject;

import org.json.JSONObject;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface RegisterInteractor{

    interface OnCreatingUserFinishedListener{
        void onFinished(BaseResultObject items);
        void onFailure(BaseResultObject items);
    }
    void insertUserForSignup(OnCreatingUserFinishedListener listener, JSONObject jsonObject, Context context);
}
