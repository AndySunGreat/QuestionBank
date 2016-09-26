package com.aladdin.apps.questionbank.question.bank;

import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.data.bean.QuestionChooseItem;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/9/24.
 */
public class QuestionFeatureBankInteractorImpl implements QuestionFeatureBankInteractor{

    private Question question;
    private QuestionChooseItem quesChooseItem;
    private BaseResultObject bro;
    @Override
    public void getOrderQuestionsByBankId(final OnOrderQuestionFeatureFinishedListener listener,RequestParams params){
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.getQuestionsByBankIDUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                JSONObject obj,chooseObj;
                JSONArray answersItems;
                QuestionChooseItem qChooseItem;
                Log.d("statusCode","200");
                // called when response HTTP status is "200 OK"
                // progress.hide();
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                bro.setResultJSONArray(response);
                // 存储数组变量
                List<Question> objects = new ArrayList<Question>();
                for (int i = 0; i < response.length(); i++) {
                    question = new Question();
                    try {
                        // 获取具体的一个JSONObject对象
                         obj = response.getJSONObject(i);
                        question.setQuestionId(obj.getLong("questionId"));
                        //question.setQuestionCategoryId(obj.getLong("questionCategoryId"));
                        question.setQuestionBankName(obj.getString("questionBankName"));  // 题库名称
                        question.setQuestionCategoryName(obj.getString("questionCategoryName")); // 技术范畴 Java C
                        question.setQuestionTypeName(obj.getString("questionTypeName")); // 单选,多选,判断
                        question.setQuestionSubject(obj.getString("questionSubject")); // 问题标题
                        answersItems = obj.getJSONArray("chooseItemsList");
                        List<QuestionChooseItem> qChooseItemList = new ArrayList<QuestionChooseItem>();
                        for(int j=0;j<answersItems.length();j++){
                            qChooseItem = new QuestionChooseItem();
                            chooseObj = answersItems.getJSONObject(j);
                            qChooseItem.setQiSeq(chooseObj.getString("qiSeq"));
                            qChooseItem.setQiContent(chooseObj.getString("qiContent"));
                            qChooseItem.setQiComment(chooseObj.getString("qiComment"));
                            qChooseItem.setIfCorrect(chooseObj.getBoolean("ifCorrect"));
                            qChooseItemList.add(qChooseItem);
                        }
                        question.setAnswerItemsList(qChooseItemList);
                        question.setAnswers(obj.getString("answers"));
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    objects.add(question);
                }
                listener.onFinished(objects);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content,
                                  Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode","4XX");
                // Hide Progress Dialog
                //progress.hide();
                String resultErrorMsg = "";
                // When Http response code is '404'
                if (statusCode == 404) {
                    resultErrorMsg="Requested resource not found";
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    resultErrorMsg= "Something went wrong at server end";
                }
                // When Http response code other than 404, 500
                else {
                    resultErrorMsg= "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]";
                }
                bro.setResultStateCode(statusCode);
                bro.setResultMsg(resultErrorMsg);
                bro.setResultJSONArray(null);
                listener.onFailure(bro);
            }
        });
    }


}
