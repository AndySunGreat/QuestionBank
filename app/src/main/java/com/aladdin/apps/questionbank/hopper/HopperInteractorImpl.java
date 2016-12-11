package com.aladdin.apps.questionbank.hopper;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Question;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by AndySun on 2016/10/8.
 */
public class HopperInteractorImpl implements HopperInteractor {
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
    /**
     *
     * @param listener
     * @param map 1.bankId   2.wrong question ids来查询
     * @param params
     */
/*    @Override
    public void getQuestionsByBankId(final OnShowingQuestionsFinishedListener listener, Map map, RequestParams params){

        bro = new BaseResultObject();
        if(map.get("bankId")!=null){
            bankId = map.get("bankId").toString();
        }
        String url = "";
        // WRONG AGAIN
        if("WRONGAGAIN".equals(map.get("orderStatus")) && map.get("prevWrongQuestIds")!=null){
            url = Constants.restfulEndpoints + Constants.getQuestionsByQuestionIdsUrl;
            params.put("questionIds",map.get("prevWrongQuestIds"));
        }else{
            // AGAIN,NEW
            url = Constants.restfulEndpoints + Constants.getQuestionsByBankIDPartOneUrl;
            params.put("bankId",bankId);
            // url = Constants.restfulEndpoints + Constants.getQuestionsByBankIDPartOneUrl + bankId;
        }

        Log.d("QuestDAOUrl:",url);
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
                List<QuestionSubEntity> questionItemList = new ArrayList<QuestionSubEntity>();
                QuestionSubEntity questionItemTemp;
                // 循环遍历auoRecommendPackages
                for (int i = 0; i < response.length(); i++) {
                    question = new Question();
                    questionItemList = new ArrayList<QuestionSubEntity>();
                    try {
                        obj = response.getJSONObject(i);
                        question.setQuestionId(obj.getLong("questionId"));
                        question.setQuestContent(obj.getString("questContent"));
                        question.setBankId(obj.getLong("bankId"));
                        //sdf =new SimpleDateFormat("yyyy-MM-dd");
                        strCreateDate = obj.getString("changeDate");
                        question.setChangeDate(strCreateDate);
*//*                        if(strChangeDate!=null){
                            question.setChangeDate((java.util.Date)sdf.parse(strChangeDate));
                        }*//*
                        question.setCorrectIndexes(obj.getString("correctIndexes"));
                        question.setCorrectAnswer(obj.getString("correctAnswer"));
                        question.setQuestOptionsJson(obj.getString("questOptionsJson"));
                        question.setQuestType(obj.getString("questType"));
                        jsonArrayTemp = (JSONArray) obj.get("questOptions");
                        for(int w=0;w<jsonArrayTemp.length();w++){
                            jsonObjectTemp = (JSONObject)jsonArrayTemp.get(w);
                            questionItemTemp = new QuestionSubEntity();
                            questionItemTemp.setOptSeq(jsonObjectTemp.getString("optSeq"));
                            questionItemTemp.setOptContent(jsonObjectTemp.getString("optContent"));
                            questionItemList.add(questionItemTemp);
                        }
                        question.setQuestOptions(questionItemList);
                    }catch(JSONException e){
                        e.printStackTrace();
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
    public void createNewAnswerRecord(final OnCreatingAnswerFinishedListener listener,JSONObject jsonObjectParam, final Context context) {
        bro = new BaseResultObject();
        String url = Constants.restfulEndpoints + Constants.postBankAnswersUrl;
        Log.d("url:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        //StringEntity entity = null;
        ByteArrayEntity entity = null;
        try {
            Log.d("creatAnswerParamer:",jsonObjectParam.toString());
           // answer_id, order_id,score,wrong_quest_ids,create_date,bank_id
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
                    Log.d("afterNewAnswer-OrderId",jsonObject.getLong("orderId")+"");
                    bankAnswers.setWrongQuestIds(jsonObject.getString("wrongQuestIds"));
                }catch (JSONException e){
                    e.printStackTrace();
                }

                listener.onCreateAnswerFinished(bankAnswers,context);
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
                listener.onCreateAnswerFailure(bro,context);
            }
        });
    }


    @Override
    public void updateOrderStatus(final OnUpdatingOrderFinishedListener listener, JSONObject jsonObjectParam, final Context context) {
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
            Log.d("updOrderStatusParame:",jsonObjectParam.toString());
            // answer_id, order_status
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
                    Log.d("afterUpdateOrderId",String.valueOf(jsonObject.getLong("orderId")));
                    map.put("answerId",jsonObject.getString("answerId"));
                    Log.d("afterUpdateAnswerId",jsonObject.getString("answerId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                bro.setResultDataMap(map);
                listener.onUpdateOrderFinished(bro,context);
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
                listener.onUpdateOrderFailure(bro,context);
            }
        });
    }*/




}
