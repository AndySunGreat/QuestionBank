package com.aladdin.apps.questionbank.answers;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Order;
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
            answersView.showAnswerInfo();
            answersView.showBottomButtons();
            map = answersView.getFilterParamsByIntent();
        }

        RequestParams params = new RequestParams();
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

    // 当用户点击"继续"按钮时，即进入下一题库根据package的配置。
    // 1.先根据package表的bank_ids来生成新的order数据
    // 2.跳回QuestionActivity会初始化调用getQuestionsByBankId()
    // 3.若是bank_ids所有order都已经完成，则显示两个路径，1.能力评估  2.新题库推荐
    @Override
    public void onNextBankBtnClick(View view,JSONObject jsonObject) {

        /*  jsonObject.put("score",String.valueOf(getIntent().getLongExtra("score",1L)));
            jsonObject.put("wrongQuestIds",getIntent().getStringExtra("wrongQuestIds"));
            jsonObject.put("oldBankId",String.valueOf(getIntent().getLongExtra("bankId",1L)));
            jsonObject.put("answerId",String.valueOf(getIntent().getLongExtra("answerId",1L)));
            jsonObject.put("orderId",getIntent().getStringExtra("orderId"));
            jsonObject.put("packageId",getIntent().getStringExtra("packageId"));
            jsonObject.put("bankIds",getIntent().getStringExtra("bankIds"));
            */
        String[] bankIdsArray;
        String oldBankId;
        int bankIdsArrayLength=0;
        int newBankIdIndex =0;
        try {
            bankIdsArray = jsonObject.get("bankIds").toString().split(",");
            bankIdsArrayLength = bankIdsArray.length;
            oldBankId = jsonObject.get("oldBankId").toString();
            // 判断是否是最后一题库
            if(!bankIdsArray[bankIdsArrayLength-1].equals(oldBankId)){
                for(int i=0;i<bankIdsArray.length;i++){
                    newBankIdIndex = 0;
                    if(bankIdsArray[i].equals(oldBankId)){
                        newBankIdIndex =i+1;
                        break;
                    }
                }
                jsonObject.put("newBankId",bankIdsArray[newBankIdIndex]);
            }else{
                Log.d("这已经是最后一个题库了","New Recommand");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        answersInteractor.doNextBank(this,jsonObject,view.getContext());
    }


    // 当用户点击“错题”按钮进行复习时
    // 1.生成新的answer数据
    // 2.将order记录状态置回"FIXED",将answerID添加到answerIDs.
    // 当用户点击"重新做一遍"按钮时，
    // 1.生成新的answer数据
    // 2.将order记录状态置回"AGAINED",将answerID添加到answerIDs.
    @Override
    public void onAnswerAgainBtnClick(View view,JSONObject jsonObject) {
        try {
            jsonObject.put("orderStatus","AGAIN");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        answersInteractor.doAnswerAgain(this,jsonObject,view.getContext());
    }

    @Override
    public void onWrongAnswerAgainBtnClick(View view,JSONObject jsonObject) {
        try {
            jsonObject.put("orderStatus","WRONGAGAIN");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        answersInteractor.doAnswerAgain(this,jsonObject,view.getContext());
    }


    @Override
    public void onAnswerAgainFinished(Order items) {
        if (answersView != null) {
            // 是否显示更新订单状态成功
            Log.d("updateOrder:","Successful");
            answersView.navigateQuestionActivity(items);
        }
    }

    @Override
    public void onAnswerAgainFailure(BaseResultObject items) {

    }

    @Override
    public void onCreateOrderFinished(Order items) {
        // jump back into question activity
        if (answersView != null) {
            // 是否显示更新订单状态成功
            Log.d("updateOrder:","Successful");
            answersView.navigateQuestionActivity(items);
        }
    }

    @Override
    public void onCreateOrderFailure(BaseResultObject items) {

    }
}
