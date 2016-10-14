package com.aladdin.apps.questionbank.questions;

import com.aladdin.apps.questionbank.base.BaseModuleView;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface QuestionsView extends BaseModuleView {

    void navigateAnswerActivity(int position);

    void showProgress();

    void hideProgress();

    void setAnswers(BankAnswers mData);

    void setItems(List<Question> mData);

    void setItemsError(BaseResultObject bro);

    void showMessage(String message);

    void showTitleBar();

    Map getFilterParams();

    void showCorrectAnswer();

    BankAnswers getAnswers();
}
