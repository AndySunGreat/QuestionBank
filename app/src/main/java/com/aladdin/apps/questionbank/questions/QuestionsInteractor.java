package com.aladdin.apps.questionbank.questions;

import android.content.Context;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface QuestionsInteractor {

    // Loading auto recommend packages based on jobId
    interface OnShowingQuestionsFinishedListener{
        void onShowQuestFinished(List<Question> items);
        void onShowQuestFailure(BaseResultObject items);
    }

    interface OnUpdatingOrderFinishedListener{
        void onUpdateOrderFinished(BaseResultObject items);
        void onUpdateOrderFailure(BaseResultObject items);
    }

    interface OnCreatingAnswerFinishedListener{
        void onCreateAnswerFinished(BankAnswers items);
        void onCreateAnswerFailure(BaseResultObject items);
    }

    // AHC calling for getting auto recommend packages based on JobId
    void getQuestionsByBankId(OnShowingQuestionsFinishedListener listener, Map map, RequestParams params);

    // update order status as completed
    void updateOrderStatus(OnUpdatingOrderFinishedListener listener, JSONObject jsonObjectParam, Context context);

    void createNewAnswerRecord(OnCreatingAnswerFinishedListener listener, JSONObject jsonObject, Context context);




}
