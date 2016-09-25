package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public interface QuestionModuleBankInteractor {

    interface OnOrderQuestionBankFinishedListener {
        void onFinished(List<ChannelRow> items);
        void onFailure(BaseResultObject items);
    }
    void getOrderQuestionBankByUserId(OnOrderQuestionBankFinishedListener listener,RequestParams params);
}
