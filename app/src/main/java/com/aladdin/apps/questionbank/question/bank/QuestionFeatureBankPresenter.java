package com.aladdin.apps.questionbank.question.bank;

/**
 * Created by AndySun on 2016/9/24.
 */
public interface QuestionFeatureBankPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
