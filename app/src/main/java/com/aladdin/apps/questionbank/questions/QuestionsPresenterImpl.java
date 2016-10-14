package com.aladdin.apps.questionbank.questions;

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
public class QuestionsPresenterImpl implements QuestionsPresenter,
        QuestionsInteractor.OnShowingQuestionsFinishedListener,
        QuestionsInteractor.OnCreatingAnswerFinishedListener,
        QuestionsInteractor.OnUpdatingOrderFinishedListener {

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

    /**
     *             业务逻辑：
     1) 当用户答题提交后，会先给用户整套题打个分值，将分值以及错题记录在下边表中，并更新order表的order status为”completed”。
     Whole API: /api/bank/order/submit  [POST]

     表：user_answer（用户每做完一次该套题都会插入一条记录）
     API: /api/bank/order/{orderId}  [GET]--获得当前题库order
     API: /api/bank/order/{orderId}/answer/{answerId} [POST] –保存用户答题情况（包括错误题以及分值）
     API: /api/bank/order/{orderId} [PUT] –更新当前用户题库order的status
     */
    @Override
    public void submitAllAnswers(JSONObject jsonObject,View v) {
        RequestParams params = new RequestParams();
        // 1.生成新的answer记录，获得answerId以便更新到Order信息表中,包括打分逻辑放到后端
        questionsInteractor.createNewAnswerRecord(this,jsonObject,v.getContext());
        BankAnswers bankAnswers = questionsView.getAnswers();
        try {
            jsonObject.put("answerId",bankAnswers.getAnswerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 2.更新order表的状态为"Completed"以及更新answerId字段
        questionsInteractor.updateOrderStatus(this,jsonObject,v.getContext());
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
    public void onShowQuestFinished(List<Question> items) {
        if (questionsView != null) {
            questionsView.setItems(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onShowQuestFailure(BaseResultObject items) {
        if (questionsView != null) {
            questionsView.setItemsError(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onUpdateOrderFailure(BaseResultObject items) {
        if (questionsView != null) {
            questionsView.setItemsError(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onCreateAnswerFailure(BaseResultObject items) {
        if (questionsView != null) {
            questionsView.setItemsError(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onCreateAnswerFinished(BankAnswers items) {
        if (questionsView != null) {
            questionsView.setAnswers(items);
            questionsView.hideProgress();
        }
    }

    @Override
    public void onUpdateOrderFinished(BaseResultObject items) {
        if (questionsView != null) {
            // 是否显示更新订单状态成功
            Log.d("updateOrder:","Successful");
            //questionsView.setItems(items);
            //questionsView.hideProgress();
        }
    }


}
