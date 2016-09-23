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
import com.aladdin.apps.questionbank.component.listview.ChannelRow;
import com.aladdin.apps.questionbank.component.listview.ListViewAdapter;
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
                if(position==0){
                    intent = new Intent(getActivity(),QuestionModuleActivity.class);
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
        mData1.add(new ChannelRow(R.drawable.ic_favorites, "我的题库"));
        mData1.add(new ChannelRow(R.drawable.ic_recents, "充电值"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "最新热库"));
        mData1.add(new ChannelRow(R.drawable.ic_restaurants, "题库重置"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "挑战高手"));
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
