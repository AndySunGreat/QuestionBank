package com.aladdin.apps.questionbank.home.hm;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/10/19.
 */
public class HomeChannelPresenterImpl implements HomeChannelPresenter,
        HomeChannelInteractor.OnShowingOrdersFinishedListener,HomeChannelInteractor.OnShowingPackagesFinishedListener{

    private HomeChannelView homeChannelView;
    private HomeChannelInteractor homeChannelInteractor;
    private Map map = new HashMap();
    RequestParams params = new RequestParams();
    public HomeChannelPresenterImpl(HomeChannelView homeChannelView,
                                    HomeChannelInteractor homeChannelInteractor) {
        this.homeChannelView = homeChannelView;
        this.homeChannelInteractor = homeChannelInteractor;
    }

    @Override
    public void onResume() {
        if (homeChannelView != null) {
            //homeChannelView.showTitleBar();
            //homeChannelView.showProgress();
            //map = homeChannelView.getFilterParams();
            homeChannelInteractor.getOrderInfoByUserId(this,map,params);

        }
    }

    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id) {
        // 1.继续答题[Question]
        if(position==0){
            // 1.根据用户userId来查询orders表，获得用户套餐信息(packageId,bankIds),当前题库bankId,当前订单orderId&orderStatus
            // 2.将如下信息传到QuestionActivity
            Log.v("首页频道", "你点击了该频道条目" + position + id);
            homeChannelView.navigateQuestionAcitivity();

        }else if(position==1){
            // 题库推荐类型：主要是生成针对用户能力劣势的题库，题库内容也更多更全面，稳定发展型。
            // 2.能力指数[Indexes]：主要针对当前在岗用户的能力提升的需求。
            // 2.1 Overview: 根据用户userId来获得用户各项能力系数(indexes表->能力项 & 能力值 & 能力层次level )
            // 该表是由定时脚本根据用户的answers表情况进行统计生成数据到该表中来。
            // 2.2 Analysis[SWOT]:再根据用户jobId和用户工作年限等信息参数来计算和比较该用户当前是否在当地区域该职业有竞争力
            // 2.3 Solution: 根据以上分析结果为用户生成对应的题库来。

        }else if(position==2){
            // 3.我要跳槽[Hopper]
            // 题库推荐类型：主要是生成针对用户面试题准备，题库以精准为宜，快速精巧型，从时间上求精。
            // 3.1 用户选择跳槽类型,根据如下种种跳槽理由来进行推荐。
            //   A.谋求提升薪酬型：不换区域不换行业不换岗位不换专业方向
            //   B.谋求离家近：不换区域
            // 3.2 提供“猎头池”、“公司分析”、“薪酬【谈判及分析】”等多个模块
            // 3.3 Solution: 根据以上用户选择结果来生成针对面试的题库来【面试型题库】。
            homeChannelView.navigateHopperActivity();

        }else if(position==3){
            // 4.我要升职[Promotion]，从职业角度来看。
            // 题库推荐类型：主要是生成针对用户想一个Level提升到另一个Level，修改用户套餐，套餐变更型,比如从Java SE L1 -》L2。

        }else if(position==4){
            // 5.充电+[Charge]，从知识范畴来看。
            // 题库推荐类型：主要是生成针对用户自定义想学一些新的知识、技术，或是加宽、加肥自己所会的技能，比如从CoreJava -L1到L2。
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onShowingOrdersFinished(List<Order> items) {
        if (homeChannelView != null) {
            homeChannelView.setOrdersItems(items);
            Log.d("OrdersFinished","HomeChannelPresenterImpl-success");
            //homeChannelView.hideProgress();
        }
        Log.d("bankIds",homeChannelView.getFilterMap().get("bankIds")+"");
        homeChannelInteractor.getPackageInfoById(this,homeChannelView.getFilterMap(),params);

    }

    @Override
    public void onShowingPackagesFinished(List<Package> items) {
        if (homeChannelView != null) {
            homeChannelView.setPackageItems(items);
            Log.d("PackagesFinished","HomeChannelPresenterImpl-success");
            //homeChannelView.hideProgress();
        }
    }

    @Override
    public void onShowingOrdersFailure(BaseResultObject items) {
        if (homeChannelView != null) {
            homeChannelView.setOrdersItemsError(items);
            //homeChannelView.hideProgress();
        }
    }

    @Override
    public void onShowingPackagesFailure(BaseResultObject items) {
        if (homeChannelView != null) {
            homeChannelView.setPackageItemsError(items);
            //homeChannelView.hideProgress();
        }
    }
}
