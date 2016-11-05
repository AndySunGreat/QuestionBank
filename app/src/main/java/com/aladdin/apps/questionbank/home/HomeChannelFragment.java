package com.aladdin.apps.questionbank.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.common.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.questions.QuestionsActivity;

import java.util.ArrayList;


/**
 * Created by AndySun on 2016/9/19.
 */
public class HomeChannelFragment extends Fragment {

    ListView mainListView;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter  myAdapter1;
    Intent intent = new Intent();
    private ArrayAdapter<String> listAdapter ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_channel_listview, container, false);
        mainListView = (ListView) view.findViewById( R.id.quescContentListview );
        createQuesChlContent();
         /*为ListView添加点击事件*/
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 1.继续答题[Question]
                if(position==0){
                    // 1.根据用户userId来获得用户套餐信息(packageId,bankIds),当前题库bankId,当前订单orderId&orderStatus
                    // 2.将如下信息传到QuestionActivity
                    intent = new Intent(getActivity(), QuestionsActivity.class);
                    intent.putExtra("userId","");
                    Log.v("首页频道", "你点击了该频道条目" + position + id);
                    /* map.put("packageId",currentIntent.getStringExtra("packageId"));
                    map.put("orderStatus",currentIntent.getStringExtra("orderStatus"));
                    map.put("bankId",currentIntent.getStringExtra("bankId"));
                    map.put("bankIds",currentIntent.getStringExtra("bankIds"));
                    map.put("orderId",currentIntent.getStringExtra("orderId"));*/
                    startActivity(intent);

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


                }else if(position==3){
                    // 4.我要升职[Promotion]，从职业角度来看。
                    // 题库推荐类型：主要是生成针对用户想一个Level提升到另一个Level，修改用户套餐，套餐变更型,比如从Java SE L1 -》L2。

                }else if(position==4){
                    // 5.充电+[Charge]，从知识范畴来看。
                    // 题库推荐类型：主要是生成针对用户自定义想学一些新的知识、技术，或是加宽、加肥自己所会的技能，比如从CoreJava -L1到L2。
                }

            }
        });

        return view;
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
