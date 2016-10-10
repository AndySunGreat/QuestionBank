package com.aladdin.apps.questionbank.packages;

import android.content.Intent;

import com.aladdin.apps.questionbank.base.BaseModuleView;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface PackagesView extends BaseModuleView {

    void navigateQuestionActivity();

    void showProgress();

    void hideProgress();

    void setItems(List<Package> mData);

    void setItemsError(BaseResultObject bro);

    void showMessage(String message);

    void showTitleBar();

    Map getFilterParams();
}
