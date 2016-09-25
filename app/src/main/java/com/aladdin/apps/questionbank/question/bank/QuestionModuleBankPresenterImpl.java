package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public class QuestionModuleBankPresenterImpl implements QuestionModuleBankPresenter, QuestionModuleBankInteractor.OnOrderQuestionBankFinishedListener{

    private QuestionModuleBankView quesModuleBankView;
    private QuestionModuleBankInteractor quesModuleBankInteractor;

    public QuestionModuleBankPresenterImpl(QuestionModuleBankView quesModuleBankView,
                                           QuestionModuleBankInteractor quesModuleBankInteractor) {
        this.quesModuleBankView = quesModuleBankView;
        this.quesModuleBankInteractor = quesModuleBankInteractor;
    }

    @Override
    public void onResume(){
        if (quesModuleBankView != null) {
            quesModuleBankView.showTitleBar();
            quesModuleBankView.showProgress();
        }
        RequestParams params = new RequestParams();
        quesModuleBankInteractor.getOrderQuestionBankByUserId(this,params);
    }

    @Override
    public void onItemClicked(int position){
        if (quesModuleBankView != null) {
            quesModuleBankView.showMessage(String.format("Position %d clicked", position + 1));
        }
        quesModuleBankView.navigateFeaturesActivity(position);
    }

    @Override
    public void onDestroy(){
        quesModuleBankView = null;
    }

    @Override
    public void onFinished(List<ChannelRow> items) {
        if (quesModuleBankView != null) {
            quesModuleBankView.setItems(items);
            quesModuleBankView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (quesModuleBankView != null) {
            quesModuleBankView.setItemsError(items);
            quesModuleBankView.hideProgress();
        }
    }
}
