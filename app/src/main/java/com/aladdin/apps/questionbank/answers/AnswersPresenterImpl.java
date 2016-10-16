package com.aladdin.apps.questionbank.answers;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class AnswersPresenterImpl implements AnswersPresenter,
        AnswersInteractor.OnNextBankFinishedListener,
        AnswersInteractor.OnAnswerAgainFinishedListener {

    private AnswersView answersView;
    private AnswersInteractor answersInteractor;
    private Map map;
    public AnswersPresenterImpl(AnswersView answersView,
                                  AnswersInteractor answersInteractor) {
        this.answersView = answersView;
        this.answersInteractor = answersInteractor;
    }

    @Override
    public void onResume(){
        if (answersView != null) {
            answersView.showTitleBar();
            answersView.showProgress();
            map = answersView.getFilterParamsByIntent();
            Log.d("bankId1",map.get("bankId").toString());
        }
        RequestParams params = new RequestParams();
        Log.d("bankId2",map.get("bankId").toString());
        //answersInteractor.getAnswersByBankId(this,map,params);
    }





    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id){
        if (answersView != null) {
            answersView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy(){
        answersView = null;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onAnswerAgainFinished(BaseResultObject items) {

    }

    @Override
    public void onAnswerAgainFailure(BaseResultObject items) {

    }

    @Override
    public void onCreateOrderFinished(BaseResultObject items) {

    }

    @Override
    public void onCreateOrderFailure(BaseResultObject items) {

    }
}
