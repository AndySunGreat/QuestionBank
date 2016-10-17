package com.aladdin.apps.questionbank.answers;

import android.content.Context;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface AnswersInteractor {

    // 当用户点击"继续"按钮时，即进入下一题库根据package的配置。
    // 1.先根据package表的bank_ids来生成新的order数据
    // 2.跳回QuestionActivity会初始化调用getQuestionsByBankId()
    // 3.若是bank_ids所有order都已经完成，则显示两个路径，1.能力评估  2.新题库推荐
    interface  OnNextBankFinishedListener{
        void onCreateOrderFinished(Order items);
        void onCreateOrderFailure(BaseResultObject items);
    }

    // 当用户点击“错题”按钮进行复习时
    // 1.生成新的answer数据
    // 2.将order记录状态置回"FIXED",将answerID添加到answerIDs.
    // 当用户点击"重新做一遍"按钮时，
    // 1.生成新的answer数据
    // 2.将order记录状态置回"AGAINED",将answerID添加到answerIDs.
    interface  OnAnswerAgainFinishedListener{
        void onAnswerAgainFinished(BaseResultObject items);
        void onAnswerAgainFailure(BaseResultObject items);
    }
    void doNextBank(OnNextBankFinishedListener listener, JSONObject jsonObjectParam, Context context);

    void doAnswerAgain(OnNextBankFinishedListener listener, JSONObject jsonObjectParam, Context context);

            /*


2) 当(1)逻辑处理完成后，会跳转到业务选择页面，该页面提供用户三种选择：
A.重新做一遍
B.进行下一环节（取决于package设置）
C.错题复习
	选择“A”，则向order表新插入一条记录（order status : new），该题库的;
WHOLE API: /api/bank/order/new
	选择“B”，则会根据order表的package Id查询package表，用户下一环节要做的题库是那一个，然后生成新的order，插入到order 表。
WHOLE API: /api/bank/order/next
	选择“C”，则会生成一条新的answer表记录，并更新order 表的change date以及将新的answer Id添加到order表的该条记录的answer Ids字段中，并更新last_answer_id。
WHOLE API: /api/bank/order/wrong
3) 当用户的该package所有题库都做完的时候，会跳转到“充电频道”的“能力评估”页面，会根据用户当前答题情况和用户所有时间、用户当前职业、用户目标进行判断，显示用户能力各项参数，以及推荐用户选择订制新的套餐。
WHOLE API: /api/charge/evaluate/{userId}

             */
         /*intent = new Intent(getApplicationContext(),AnswersActivity.class);
*//*        try {
            intent.putExtra("jobId", jsonObject.getString("jobId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*//*
        Log.d("navigateAnswerActivity","navigateAnswerActivity");
        startActivity(intent);*/
}
