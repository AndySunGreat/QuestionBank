package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.data.bean.Question;

import java.util.List;

/**
 * Created by AndySun on 2016/9/26.
 */
public interface QuestionFeatureBankView {
    void showProgress();

    void hideProgress();

    void setItems(List<Question> mData);

    void setItemsError(BaseResultObject bro);

    void showMessage(String message);

    //void navigateFeaturesActivity(int position);

    void showTitleBar();
}
