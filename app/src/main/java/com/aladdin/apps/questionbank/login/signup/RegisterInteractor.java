package com.aladdin.apps.questionbank.login.signup;

import android.content.Context;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface RegisterInteractor {

    interface OnCreatingUserFinishedListener{
        void onFinished(BaseResultObject items);
        void onFailure(BaseResultObject items);
    }
    void insertUserForSignup(OnCreatingUserFinishedListener listener, JSONObject jsonObject, Context context);
}
