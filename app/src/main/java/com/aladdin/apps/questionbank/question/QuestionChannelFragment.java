package com.aladdin.apps.questionbank.question;

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
import com.aladdin.apps.questionbank.component.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.question.bank.QuestionModuleBankActivity;

import java.util.ArrayList;


/**
 * Created by AndySun on 2016/9/19.
 */
public class QuestionChannelFragment extends Fragment {

    ListView mainListView;
    private ArrayAdapter<String> listAdapter ;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter  myAdapter1;
    Intent intent = new Intent();
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_c_content_listview, container, false);
        mainListView = (ListView) view.findViewById( R.id.quescContentListview );
        createQuesChlContent();
         /*为ListView添加点击事件*/
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.v("BaseAdapterTest", "你点击了ListView条目" + position + id);
                // jump to first module '我的题库[进入答题]'
                if(position==0){
                    // TODO: 2016-10-9 big change: directly get first bank's questions based on userId/packageId/orderId/bankId

                    intent = new Intent(getActivity(),QuestionModuleBankActivity.class);
                    intent.putExtra("testData","dsfds");
                    startActivity(intent);
                }
            }
        });

        return view;
    }


    private void createQuesChlContent() {
        //数据初始化
        mData1 = new ArrayList<ChannelRow>();
        mData1.add(new ChannelRow(R.drawable.ic_favorites, "开始答题"));
        mData1.add(new ChannelRow(R.drawable.ic_recents, "热门题库"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "面试策略"));
        mData1.add(new ChannelRow(R.drawable.ic_restaurants, "Boss战"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby,"薪酬谈判"));
        //Adapter初始化
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData1, R.layout.ques_c_content_listview_row) {
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
