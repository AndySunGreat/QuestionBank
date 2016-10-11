package com.aladdin.apps.questionbank.questions;

import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.packages.PackagesInteractor;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/10/8.
 */
public class QuestionsInteractorImpl implements QuestionsInteractor {
    private BaseResultObject bro;
    private List<Question> questionList;
    private JSONObject obj;
    private Question question;
    private SimpleDateFormat sdf;
    private String strCreateDate;
    private String strChangeDate;
    private java.util.Date date;
    private String bankId;
    @Override
    public void getQuestionsByBankId(final OnShowingQuestionsFinishedListener listener, Map map, RequestParams params){
        bro = new BaseResultObject();
        if(map.get("bankId")!=null){
            bankId = map.get("bankId").toString();
        }
        String url = Constants.restfulEndpoints + Constants.getQuestionsByBankIDPartOneUrl + bankId;
        Log.d("Pkg-url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                super.onSuccess(statusCode, headers, response);
                bro.setResultStateCode(statusCode);
                bro.setResultMsg("Success");
                bro.setResultJSONArray(response);
                questionList = new ArrayList<Question>();
                // 循环遍历auoRecommendPackages
                for (int i = 0; i < response.length(); i++) {
                    question = new Question();
                    try {
                        obj = response.getJSONObject(i);
                        question.setQuestionId(obj.getLong("questionId"));
                        question.setQuestContent(obj.getString("questContent"));
                        question.setBankId(obj.getLong("bankId"));
                        //sdf =new SimpleDateFormat("yyyy-MM-dd");
                        strCreateDate = obj.getString("changeDate");
                        question.setChangeDate(strCreateDate);
/*                        if(strChangeDate!=null){
                            question.setChangeDate((java.util.Date)sdf.parse(strChangeDate));
                        }*/
                        question.setCorrectAnswer(obj.getString("correctAnswer"));
                        question.setQuestOptionsJson(obj.getString("questOptionsJson"));
                        question.setQuestType(obj.getString("questType"));
                    }catch(JSONException e){
                        e.printStackTrace();
/*                    }catch (ParseException e2){
                        e2.printStackTrace();*/
                    }
                    questionList.add(question);
                }
                listener.onFinished(questionList);
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
