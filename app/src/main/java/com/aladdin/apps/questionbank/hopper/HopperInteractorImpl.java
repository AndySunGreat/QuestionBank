package com.aladdin.apps.questionbank.hopper;

import android.content.Context;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperPositionsEntity;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperPositionsSubEntity;
import com.aladdin.apps.questionbank.data.bean.HopperPositions;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cz.msebera.android.httpclient.Header;

/**
 * Created by AndySun on 2016/10/8.
 */
public class HopperInteractorImpl implements HopperInteractor {
    private BaseResultObject bro;
    //private List<HopperPositions> hopperPositionsList;
    private JSONObject obj;
    //private HopperPositions hopperPositions;
    private HopperPositionsSubEntity hopperPositionsSubEntity;
    private HopperPositionsEntity hopperPositionsEntity;
    private List<HopperPositionsSubEntity> hopperPositionsSubEntityList;
    private List<HopperPositionsEntity> hopperPositionsEntityList;
    private SimpleDateFormat sdf;
    private String strCreateDate;
    private String strChangeDate;
    private java.util.Date date;
    private String bankId;
    private JSONObject jsonObjectParam;

    @Override
    public void queryJobListForHopper(final OnShowingJobsFinishedListener listener, Map map, RequestParams params){

        bro = new BaseResultObject();
        /*
           if(map.get("bankId")!=null){
                bankId = map.get("bankId").toString();
            }
        */
        String url = Constants.restfulEndpoints +  Constants.getPositionsUrl;
        Log.d("QuestDAOUrl:",url);
        AsyncHttpClient client = new AsyncHttpClient();
        // POST Usage: client.post(Context, URL, StringEntity, "application/json", AsyncHttpResponseHandler())
        client.get(url, params, new JsonHttpResponseHandler() {
         @Override
         public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
             super.onSuccess(statusCode, headers, response);
             bro.setResultStateCode(statusCode);
             bro.setResultMsg("Success");
             bro.setResultJSONArray(response);
             hopperPositionsEntityList = new ArrayList<HopperPositionsEntity>();
             JSONArray jsonArrayTemp;
             JSONObject jsonObjectTemp;
             String previousCity = "";
             for (int i = 0; i < response.length(); i++) {
                 hopperPositionsEntity = new HopperPositionsEntity();
                 hopperPositionsSubEntityList = new ArrayList<HopperPositionsSubEntity>();

                 try {
                     obj = response.getJSONObject(i);
/*                     if(i==0){
                         previousCity = obj.getString("city");
                     }else{
                         // 新建group
                         if(!obj.getString("city").equals(previousCity)){
                             hopperPositionsGroupEntityList.add(hopperPositionsGroupEntity);
                             hopperPositionsGroupEntity = new HopperPositionsEntity();
                         }else{
                             previousCity = obj.getString("city");
                         }
                     }*/
                     hopperPositionsEntity.setChangDate(obj.getString("changeDate"));
                     hopperPositionsEntity.setCompanyId(String.valueOf(obj.getLong("companyId")));
                     hopperPositionsEntity.setPositionId(obj.getLong("positionId"));
                     hopperPositionsEntity.setPositionName(obj.getString("positionName"));
                     hopperPositionsEntity.setGroupTitle(obj.getString("city"));
                     hopperPositionsEntity.setCity(obj.getString("city"));
                     hopperPositionsEntity.setExperience(obj.getLong("experience"));
                     hopperPositionsEntity.setSalary(obj.getString("salary"));
                     hopperPositionsEntity.setRequiredJson(obj.getString("requiredJson"));
                     jsonArrayTemp = (JSONArray) obj.get("positionsOptionList");
                     for (int w = 0; w < jsonArrayTemp.length(); w++) {
                         jsonObjectTemp = (JSONObject) jsonArrayTemp.get(w);
                         hopperPositionsSubEntity = new HopperPositionsSubEntity();
                         hopperPositionsSubEntity.setOptSeq(jsonObjectTemp.getString("optSeq"));
                         hopperPositionsSubEntity.setOptContent(jsonObjectTemp.getString("optContent"));
                         hopperPositionsSubEntity.setRequiredItem(jsonObjectTemp.getString("requiredItem"));
                         hopperPositionsSubEntity.setRequiredDegree(jsonObjectTemp.getString("requiredDegree"));
                         hopperPositionsSubEntity.setRequiredValue(jsonObjectTemp.getString("requiredValue"));
                         hopperPositionsSubEntityList.add(hopperPositionsSubEntity);
                     }
                     hopperPositionsEntity.setHopperPositionsSubEntityList(hopperPositionsSubEntityList);

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
                 hopperPositionsEntityList.add(hopperPositionsEntity);
             }
             listener.onShowJobsFinished(hopperPositionsEntityList);
         }

         @Override
         public void onFailure(int statusCode, Header[] headers, String content,
                               Throwable e) {
             // called when response HTTP status is "4XX" (eg. 401, 403, 404)
             Log.d("statusCode", "4XX");
             // Hide Progress Dialog
             //progress.hide();
             String resultErrorMsg = "";
             // When Http response code is '404'
             if (statusCode == 404) {
                 resultErrorMsg = "Requested resource not found";
             }
             // When Http response code is '500'
             else if (statusCode == 500) {
                 resultErrorMsg = "Something went wrong at server end";
             }
             // When Http response code other than 404, 500
             else {
                 resultErrorMsg = "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]";
             }
             bro.setResultStateCode(statusCode);
             bro.setResultMsg(resultErrorMsg);
             bro.setResultJSONArray(null);
             listener.onShowJobsFailure(bro);
         }
     });
 }


}
