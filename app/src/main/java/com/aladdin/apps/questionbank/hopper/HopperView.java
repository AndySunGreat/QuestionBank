package com.aladdin.apps.questionbank.hopper;

import com.aladdin.apps.questionbank.base.BaseModuleView;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperPositionsEntity;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperPositionsGroupEntity;
import com.aladdin.apps.questionbank.data.bean.HopperPositions;
import com.aladdin.apps.questionbank.data.bean.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface HopperView extends BaseModuleView {

    void navigateAnswersActivity(Map map);

    void showProgress();

    void hideProgress();

    void setShowJobItems(List<HopperPositionsEntity> mData);


    void setItems(List<Question> mData);

    void setItemsError(BaseResultObject bro);

    void showMessage(String message);

    void showTitleBar();

    Map getFilterParamsByIntent();

}
