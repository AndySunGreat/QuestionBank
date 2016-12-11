package com.aladdin.apps.questionbank.home.hm;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.common.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.hopper.HopperActivity;
import com.aladdin.apps.questionbank.questions.QuestionsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by AndySun on 2016/9/19.
 */
public class HomeChannelFragment extends Fragment implements HomeChannelView,AdapterView.OnItemClickListener{

    ListView mainListView;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter  myAdapter1;
    Intent intent = new Intent();
    private ArrayAdapter<String> listAdapter ;
    private HomeChannelPresenter homeChannelPresenter;
    private Map filterMap = new HashMap();

    @Override
    public Map getFilterMap() {
        return filterMap;
    }

    @Override
    public void setFilterMap(Map filterMap) {
        this.filterMap = filterMap;
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_channel_listview, container, false);
        mainListView = (ListView) view.findViewById( R.id.quescContentListview );
        homeChannelPresenter = new HomeChannelPresenterImpl(this,new HomeChannelInteractorImpl());
        createQuesChlContent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        homeChannelPresenter.onResume();
    }

    @Override
    public void setOrdersItems(List<Order> mData) {
        Order order = (Order)mData.get(0);
        filterMap.put("packageId",order.getPackageId());
        filterMap.put("answerId",order.getAnswerId());
        filterMap.put("orderId",order.getOrderId());
        filterMap.put("userId",order.getUserId());
        filterMap.put("bankId",order.getBankId());
        filterMap.put("orderStatus",order.getOrderStatus());
        filterMap.put("orderType",order.getOrderType());
        filterMap.put("changeDate",order.getChangeDate());
    }

    @Override
    public void setOrdersItemsError(BaseResultObject bro) {

    }

    @Override
    public void setPackageItems(List<Package> packageItems) {
        Package packageTmp = (Package)packageItems.get(0);
        filterMap.put("bankIds",packageTmp.getBankIdsJson());
        /*为ListView添加点击事件*/
        mainListView.setOnItemClickListener(this);
    }

    @Override
    public void setPackageItemsError(BaseResultObject bro) {

    }

    private void createQuesChlContent() {
        //数据初始化
        mData1 = new ArrayList<ChannelRow>();
        mData1.add(new ChannelRow(R.drawable.ic_action_account_balance, "继续答题"));
        mData1.add(new ChannelRow(R.drawable.ic_action_account_balance, "能力指数"));
        mData1.add(new ChannelRow(R.drawable.ic_action_account_balance, "我要跳槽"));
        mData1.add(new ChannelRow(R.drawable.ic_action_account_balance, "我要升职"));
        mData1.add(new ChannelRow(R.drawable.ic_action_account_balance, "充电 +"));
        //Adapter初始化
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData1, R.layout.home_channel_listview_row) {
            @Override
            public void bindView(ViewHolder holder, ChannelRow obj) {
                holder.setImageResource(R.id.rowLogo, obj.getRowIcon());
                holder.setText(R.id.rowName, obj.getRowName());
                /*为Button添加点击事件*/
                /*  holder.bt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("BaseAdapterTest", "你点击了按钮" + position);
                        //打印Button的点击信息
                    }
                });*/
            }
        };
        mainListView.setAdapter(myAdapter1);
    }

    @Override
    public void navigateQuestionAcitivity() {
        intent = new Intent(getActivity(), QuestionsActivity.class);
        intent.putExtra("userId",filterMap.get("userId").toString());
        intent.putExtra("packageId",filterMap.get("packageId").toString());
        intent.putExtra("orderStatus",filterMap.get("orderStatus").toString());
        intent.putExtra("bankId",filterMap.get("bankId").toString());
        intent.putExtra("orderId",filterMap.get("orderId").toString());
        intent.putExtra("bankIds",filterMap.get("bankIds").toString());
        startActivity(intent);
    }

    @Override
    public void navigateHopperActivity() {
        intent = new Intent(getActivity(), HopperActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        homeChannelPresenter.onItemClicked(parent, view, position,id);
    }

}
