package com.aladdin.apps.questionbank.hopper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.hopper.HopperInteractor;
import com.aladdin.apps.questionbank.hopper.HopperPresenter;
import com.aladdin.apps.questionbank.hopper.HopperView;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class HopperPresenterImpl implements HopperPresenter{

    private HopperView hopperView;
    private HopperInteractor hopperInteractor;
    private Map map;
    public HopperPresenterImpl(HopperView hopperView,
                               HopperInteractor hopperInteractor) {
        this.hopperView = hopperView;
        this.hopperInteractor = hopperInteractor;
    }

    @Override
    public void onResume(){
        if (hopperView != null) {
            hopperView.showTitleBar();
            hopperView.showProgress();
            // 从Activity传入
            map = hopperView.getFilterParamsByIntent();
        }
        RequestParams params = new RequestParams();
        //hopperInteractor.getHopperByBankId(this,map,params);
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
    public void submitSearching(JSONObject jsonObject,View v) {
        RequestParams params = new RequestParams();
        //map.put("")
        // 1.生成新的answer记录，获得answerId以便更新到Order信息表中,包括打分逻辑放到后端
        //hopperInteractor.createNewAnswerRecord(this,jsonObject,v.getContext());
    }


    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id){
        if (hopperView != null) {
            hopperView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy(){
        hopperView = null;
    }

    @Override
    public void onClick(View view) {

    }

/*    @Override
    public void onShowQuestFinished(List<Question> items) {
        if (hopperView != null) {
            hopperView.setItems(items);
            hopperView.hideProgress();
        }
    }

    @Override
    public void onShowQuestFailure(BaseResultObject items) {
        if (hopperView != null) {
            hopperView.setItemsError(items);
            hopperView.hideProgress();
        }
    }

    @Override
    public void onUpdateOrderFailure(BaseResultObject items, Context context) {
        if (hopperView != null) {
            hopperView.setItemsError(items);
            hopperView.hideProgress();
        }
    }

    @Override
    public void onCreateAnswerFailure(BaseResultObject items, Context context) {
        if (hopperView != null) {
            hopperView.setItemsError(items);
            hopperView.hideProgress();
        }
    }

    @Override
    public void onCreateAnswerFinished(BankAnswers items, Context context) {
        String orderAnswerIds = "";
        JSONObject updateParamJsonObj = new JSONObject();
        map = hopperView.getFilterParamsByIntent();
        if (hopperView != null) {
            hopperView.hideProgress();
            try {
                if(map.get("orderStatus").equals("AGAIN")) {
                    orderAnswerIds = map.get("prevAnswerId").toString() + ","
                            + String.valueOf(items.getAnswerId());
                }else if(map.get("orderStatus").equals("NEW")){
                    orderAnswerIds =  String.valueOf(items.getAnswerId());
                }else if(map.get("orderStatus").equals("WRONGAGAIN")){
                    orderAnswerIds = map.get("prevAnswerId").toString() + ","
                            + String.valueOf(items.getAnswerId());
                }
                Log.d("OrderStatus",map.get("orderStatus").toString());
                Log.d("更新状态的AnswerId:",orderAnswerIds);
                updateParamJsonObj.put("answerId",orderAnswerIds);
                updateParamJsonObj.put("orderStatus","COMPLETED");
                updateParamJsonObj.put("orderId",items.getOrderId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // 2.更新order表的状态为"Completed"以及更新answerId字段
            hopperInteractor.updateOrderStatus(this,updateParamJsonObj,context);
        }
    }

    @Override
    public void onUpdateOrderFinished(BaseResultObject items, Context context) {
        if (hopperView != null) {
            // 是否显示更新订单状态成功
            Log.d("updateOrder:","Successful");
            hopperView.navigateAnswersActivity(items.getResultDataMap());
        }
    }*/
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
