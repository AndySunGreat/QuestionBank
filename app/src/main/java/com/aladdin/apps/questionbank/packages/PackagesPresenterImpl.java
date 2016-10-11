package com.aladdin.apps.questionbank.packages;

import android.app.DownloadManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

/**
 * Created by AndySun on 2016/9/24.
 */
public class PackagesPresenterImpl implements PackagesPresenter,
        PackagesInteractor.OnShowingPackagesFinishedListener,
        PackagesInteractor.OnCreatingOrderFinishedListener{

    private PackagesView packagesView;
    private PackagesInteractor packagesInteractor;
    private Map map;
    public PackagesPresenterImpl(PackagesView packagesView,
                                 PackagesInteractor packagesInteractor) {
        this.packagesView = packagesView;
        this.packagesInteractor = packagesInteractor;
    }

    @Override
    public void onResume(){
        if (packagesView != null) {
            packagesView.showTitleBar();
            packagesView.showProgress();
            map = packagesView.getFilterParams();
        }
        RequestParams params = new RequestParams();
        packagesInteractor.getAutoPackagesByJobId(this,map,params);
    }

    // 选择套餐
    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id){
        String strPackageId = "";
        String strBankIds = "";
        if (packagesView != null) {
            packagesView.showMessage(String.format("您选择了第 %d 个套餐", position + 1));
            // Package ID
            strPackageId = ((TextView)view.findViewById(R.id.hiddenPackageId)).getText().toString();
            Log.d("strPackageId",strPackageId);
            strBankIds = ((TextView)view.findViewById(R.id.hiddenBankIds)).getText().toString();
            Log.d("strBankIds",strBankIds);
        }
        JSONObject jsonObject = new JSONObject();
        String[] bankIdArray = strBankIds.split(",");
        String strUserId ="";
        try {
            PersistentCookieStore myCookieStore = new PersistentCookieStore(view.getContext());
            List<Cookie> newCookie = myCookieStore.getCookies();
            Log.d("newCookie.size()",newCookie.size()+"");
            for(int i=0;i<newCookie.size();i++){
                Cookie cookie = (Cookie)newCookie.get(i);
                String cookieName = cookie.getName();
                Log.d("cookieName:",cookieName);
                String cookieValue = cookie.getValue();
                if(cookieName.equals("userId")){
                    strUserId = cookieValue;
                }
                Log.d("cookieValue:",cookieValue);
            }
            jsonObject.put("userId",strUserId);
            jsonObject.put("orderType","1"); //1-bank,hardcode
            jsonObject.put("orderStatus","NEW");
            // 获得第一条bankID
            if(bankIdArray!=null && bankIdArray.length>0) {
                jsonObject.put("bankId", bankIdArray[0].toString());
            }
            //jsonObject.put("answerId",1); 该值先不填，等用户开始答题前插入一条answer记录
            jsonObject.put("packageId",strPackageId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // insert first order record by calling /api/me/order[POST] when to click 'submit' in package choosing page.
        // 当用户答完第一个题库时，去检测该package中其它bankId如果有再次创建order.
        packagesInteractor.generateOrderForPackages(this,jsonObject,view.getContext());
        // TODO: 2016-10-8 jump 'question bank channel' and then directly go to question list pages.
        packagesView.navigateQuestionActivity(jsonObject);
    }

    @Override
    public void onDestroy(){
        packagesView = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFinished(List<Package> items) {
        if (packagesView != null) {
            packagesView.setItems(items);
            packagesView.hideProgress();
        }
    }

    @Override
    public void onFinished(BaseResultObject items) {
        if (packagesView != null) {
            Log.d("Create Order","生成订单成功");
            packagesView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (packagesView != null) {
            packagesView.setItemsError(items);
            packagesView.hideProgress();
        }
    }
}
