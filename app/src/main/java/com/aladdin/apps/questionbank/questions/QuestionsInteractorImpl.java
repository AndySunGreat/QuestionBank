package com.aladdin.apps.questionbank.questions;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionItem;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.packages.PackagesInteractor;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

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
    private JSONObject jsonObjectParam;
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
                JSONArray jsonArrayTemp;
                JSONObject jsonObjectTemp;
                List<QuestionItem> questionItemList = new ArrayList<QuestionItem>();
                QuestionItem questionItemTemp;
                // 循环遍历auoRecommendPackages
                for (int i = 0; i < response.length(); i++) {
                    question = new Question();
                    questionItemList = new ArrayList<QuestionItem>();
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
                        question.setCorrectIndexes(obj.getString("correctIndexes"));
                        question.setCorrectAnswer(obj.getString("correctAnswer"));
                        question.setQuestOptionsJson(obj.getString("questOptionsJson"));
                        question.setQuestType(obj.getString("questType"));
                        jsonArrayTemp = (JSONArray) obj.get("questOptions");
                        for(int w=0;w<jsonArrayTemp.length();w++){
                            jsonObjectTemp = (JSONObject)jsonArrayTemp.get(w);
                            questionItemTemp = new QuestionItem();
                            questionItemTemp.setOptSeq(jsonObjectTemp.getString("optSeq"));
                            questionItemTemp.setOptContent(jsonObjectTemp.getString("optContent"));
                            questionItemList.add(questionItemTemp);
                        }
                        question.setQuestOptions(questionItemList);
                    }catch(JSONException e){
                        e.printStackTrace();
/*                    }catch (ParseException e2){
                        e2.printStackTrace();*/
                    }
                    questionList.add(question);
                }
                listener.onShowQuestFinished(questionList);
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
                listener.onShowQuestFailure(bro);
            }
        });
    }

    @Override
    public void submitAllAnswers(OnSubmitAllAnaswersFinishedListener listener, JSONObject jsonObjectParam, Context context) {
        String createNewAnswerUrl = Constants.restfulEndpoints + Constants.postBankAnswersUrl;
    }


    @Override
    public void createNewAnswerRecord(final OnCreatingAnswerFinishedListener listener, JSONObject jsonObjectParam, Context context) {
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.postBankAnswersUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        //StringEntity entity = null;
        ByteArrayEntity entity = null;
        try {
            // entity = new StringEntity(jsonObject.toString());
            entity = new ByteArrayEntity(jsonObjectParam.toString().getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.post(context,url,entity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject){

                Log.d("statusCode",String.valueOf(statusCode));
                Log.d("createOrderByBankId","创建Answers成功!");
                // called when response HTTP status is "200 OK"
                //bro.setResultStateCode(statusCode);
                //bro.setResultMsg("Success");
                BankAnswers bankAnswers = new BankAnswers();
                try {
                    bankAnswers.setAnswerId(jsonObject.getLong("answerId"));
                    bankAnswers.setScore(jsonObject.getLong("score"));
                    bankAnswers.setBankId(jsonObject.getLong("bankId"));
                    bankAnswers.setOrderId(jsonObject.getLong("orderId"));
                    bankAnswers.setWrongQuestIds(jsonObject.getString("wrongQuestIds"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
                listener.onCreateAnswerFinished(bankAnswers);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content, Throwable error) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode2",String.valueOf(statusCode));
                Log.d("createOrderByBankId","创建Answers失败!");
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
                listener.onCreateAnswerFailure(bro);
            }
        });
    }


    @Override
    public void updateOrderStatus(final OnUpdatingOrderFinishedListener listener, JSONObject jsonObjectParam, Context context) {
        bro = new BaseResultObject();
        String orderId = "";
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            jsonObjectParam.put("orderStatus","COMPLETED");
            orderId = jsonObjectParam.getString("orderId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Constants.restfulEndpoints + Constants.updateOrderStatusUrl + "/"+ orderId;
        Log.d("url:",url);
        //StringEntity entity = null;
        ByteArrayEntity entity = null;
        try {
            // entity = new StringEntity(jsonObject.toString());
            entity = new ByteArrayEntity(jsonObjectParam.toString().getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.put(context,url,entity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject){

                Log.d("statusCode",String.valueOf(statusCode));
                Log.d("createOrderByBankId","更新order状态为Completed成功!");
                // called when response HTTP status is "200 OK"
                bro.setResultStateCode(statusCode);
                try {
                    bro.setResultMsg(jsonObject.getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map map = new HashMap();
                try {
                    map.put("orderId",String.valueOf(jsonObject.getLong("orderId")));
                    map.put("answerId",jsonObject.getString("answerId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                bro.setResultDataMap(map);
                listener.onUpdateOrderFinished(bro);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String content, Throwable error) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("statusCode2",String.valueOf(statusCode));
                Log.d("createOrderByBankId","更新order状态为Completed失败!");
                error.printStackTrace();
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
                listener.onUpdateOrderFailure(bro);
            }
        });
    }




}
