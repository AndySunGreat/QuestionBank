package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;

import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public interface QuestionModuleBankView {
    void showProgress();

    void hideProgress();

    void setItems(List<ChannelRow> mData);

    void setItemsError(BaseResultObject bro);

    void showMessage(String message);

    void navigateFeaturesActivity(int position);

    void showTitleBar();
}
