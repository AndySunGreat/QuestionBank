package com.aladdin.apps.questionbank.questions;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface QuestionsInteractor {

    // Loading auto recommend packages based on jobId
    interface OnShowingQuestionsFinishedListener{
        void onFinished(List<Question> items);
        void onFailure(BaseResultObject items);
    }
    // AHC calling for getting auto recommend packages based on JobId
    void getQuestionsByBankId(OnShowingQuestionsFinishedListener listener, Map map, RequestParams params);


}
