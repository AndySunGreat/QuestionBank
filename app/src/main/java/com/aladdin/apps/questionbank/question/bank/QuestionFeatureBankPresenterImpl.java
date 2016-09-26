package com.aladdin.apps.questionbank.question.bank;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by AndySun on 2016/9/24.
 */
public class QuestionFeatureBankPresenterImpl implements QuestionFeatureBankPresenter, QuestionFeatureBankInteractor.OnOrderQuestionFeatureFinishedListener{

    private QuestionFeatureBankView quesFeatureBankView;
    private QuestionFeatureBankInteractor quesFeatureBankInteractor;

    public QuestionFeatureBankPresenterImpl(QuestionFeatureBankView quesFeatureBankView,
                                            QuestionFeatureBankInteractor quesFeatureBankInteractor) {
        this.quesFeatureBankView = quesFeatureBankView;
        this.quesFeatureBankInteractor = quesFeatureBankInteractor;
    }

    @Override
    public void onResume(){
        if (quesFeatureBankView != null) {
            quesFeatureBankView.showTitleBar();
            quesFeatureBankView.showProgress();
        }
        RequestParams params = new RequestParams();
        quesFeatureBankInteractor.getOrderQuestionsByBankId(this,params);
    }

    @Override
    public void onItemClicked(int position){
        if (quesFeatureBankView != null) {
            quesFeatureBankView.showMessage(String.format("Position %d clicked", position + 1));
        }
        //quesFeatureBankView.navigateFeaturesActivity(position);
    }

    @Override
    public void onDestroy(){
        quesFeatureBankView = null;
    }

    @Override
    public void onFinished(List<Question> items) {
        if (quesFeatureBankView != null) {
            quesFeatureBankView.setItems(items);
            quesFeatureBankView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (quesFeatureBankView != null) {
            quesFeatureBankView.setItemsError(items);
            quesFeatureBankView.hideProgress();
        }
    }
}
