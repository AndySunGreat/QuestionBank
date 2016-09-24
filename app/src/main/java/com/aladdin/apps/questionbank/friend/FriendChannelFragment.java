package com.aladdin.apps.questionbank.friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.component.listview.ChannelRow;
import com.aladdin.apps.questionbank.component.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.me.MeModuleAssetActivity;
import com.aladdin.apps.questionbank.me.MeModuleBankActivity;
import com.aladdin.apps.questionbank.me.MeModuleInfoActivity;
import com.aladdin.apps.questionbank.me.MeModulePlanActivity;
import com.aladdin.apps.questionbank.me.MeModuleSettingsActivity;
import com.aladdin.apps.questionbank.me.MeModuleTargetActivity;

import java.util.ArrayList;


/**
 * Created by AndySun on 2016/9/19.
 */
public class FriendChannelFragment extends Fragment {

    ListView mainListView;
    private ArrayAdapter<String> listAdapter ;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter  myAdapter1;
    Intent intent = new Intent();
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_c_content_listview, container, false);
        mainListView = (ListView) view.findViewById( R.id.mecContentListview );
        createQuesChlContent();
         /*为ListView添加点击事件*/
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("MeChannelFragment", "你点击了ListView条目" + position + id);
                // "个人信息"模块
                if(position==0){
                    intent = new Intent(getActivity(),MeModuleInfoActivity.class);
                    intent.putExtra("testData","dsfds");
                }else if(position==1){
                    // "小目标"模块
                    intent = new Intent(getActivity(),MeModuleTargetActivity.class);
                    intent.putExtra("testData","dsfds");
                }else if(position==2){
                    // "发展计划"模块
                    intent = new Intent(getActivity(),MeModulePlanActivity.class);
                    intent.putExtra("testData","dsfds");
                }else if(position==3){
                    // "我的题库"模块
                    intent = new Intent(getActivity(),MeModuleBankActivity.class);
                    intent.putExtra("testData","dsfds");
                }else if(position==4){
                    // "资产"模块
                    intent = new Intent(getActivity(),MeModuleAssetActivity.class);
                    intent.putExtra("testData","dsfds");
                }else if(position==5) {
                    // "资产"模块
                    intent = new Intent(getActivity(), MeModuleSettingsActivity.class);
                    intent.putExtra("testData", "dsfds");
                }
                startActivity(intent);
            }
        });

        return view;
    }


    private void createQuesChlContent() {
        //数据初始化
        mData1 = new ArrayList<ChannelRow>();
        mData1.add(new ChannelRow(R.drawable.ic_favorites, "个人信息"));
        mData1.add(new ChannelRow(R.drawable.ic_recents, "小目标"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "发展计划"));
        mData1.add(new ChannelRow(R.drawable.ic_restaurants, "我的题库"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "资产"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "设置"));
        //Adapter初始化
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData1, R.layout.me_c_content_listview_row) {
            @Override
            public void bindView(ViewHolder holder, ChannelRow obj) {
                holder.setImageResource(R.id.rowLogo, obj.getRowIcon());
                holder.setText(R.id.rowName, obj.getRowName());
                /*为Button添加点击事件*/
            /*    holder.bt.setOnClickListener(new OnClickListener() {
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
}
