package com.aladdin.apps.questionbank.base;

/**
 * Created by AndySun on 2016/9/25.
 */
public interface BaseModuleView {
    void showProgress();

    void hideProgress();

    void showTitleBar();

    void showMessage(String message);
}
