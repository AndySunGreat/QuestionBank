package com.aladdin.apps.questionbank.questions;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.BaseViewHolder;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class QuestionsPresenterImpl implements QuestionsPresenter, QuestionsInteractor.OnShowingQuestionsFinishedListener{

    private QuestionsView questionsView;
    private QuestionsInteractor questionsInteractor;
    private Map map;
    public QuestionsPresenterImpl(QuestionsView questionsView,
                                  QuestionsInteractor questionsInteractor) {
        this.questionsView = questionsView;
        this.questionsInteractor = questionsInteractor;
    }

    @Override
    public void onResume(){
        if (questionsView != null) {
            questionsView.showTitleBar();
            questionsView.showProgress();
            map = questionsView.getFilterParams();
            Log.d("bankId1",map.get("bankId").toString());
        }
        RequestParams params = new RequestParams();
        Log.d("bankId2",map.get("bankId").toString());
        questionsInteractor.getQuestionsByBankId(this,map,params);
    }

    @Override
    public void validateCheckedAnswer(QuestionAdapter adapter){

    }


    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id){
        if (questionsView != null) {
            questionsView.showMessage(String.format("Position %d clicked", position + 1));
        }
        questionsView.navigateAnswerActivity(position);
    }

    @Override
    public void onDestroy(){
        questionsView = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFinished(List<Question> items) {
        if (questionsView != null) {
            questionsView.setItems(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (questionsView != null) {
            questionsView.setItemsError(items);
            questionsView.hideProgress();
        }
    }
}
