package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public interface QuestionFeatureBankInteractor {

    interface OnOrderQuestionFeatureFinishedListener {
        void onFinished(List<Question> items);
        void onFailure(BaseResultObject items);
    }
    void getOrderQuestionsByBankId(OnOrderQuestionFeatureFinishedListener listener, RequestParams params);
}
