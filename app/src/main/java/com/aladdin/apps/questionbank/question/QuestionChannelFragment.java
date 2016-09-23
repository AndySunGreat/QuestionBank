package com.aladdin.apps.questionbank.question;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quesc_content_listview, container, false);
        mainListView = (ListView) view.findViewById( R.id.quescContentListview );
        createQuesChlContent();
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
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData1, R.layout.quesc_content_listview_row) {
            @Override
            public void bindView(ViewHolder holder, ChannelRow obj) {

                holder.setImageResource(R.id.rowLogo, obj.getRowIcon());
                holder.setText(R.id.rowName, obj.getRowName());
            }
        };
        mainListView.setAdapter(myAdapter1);

    }



}
