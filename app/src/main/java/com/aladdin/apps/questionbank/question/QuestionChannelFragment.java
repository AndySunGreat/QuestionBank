package com.aladdin.apps.questionbank.question;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.adapter.App;
import com.aladdin.apps.questionbank.adapter.Book;
import com.aladdin.apps.questionbank.adapter.ListViewAdapter;
import com.aladdin.apps.questionbank.adapter.ViewHolderAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AndySun on 2016/9/19.
 */
public class QuestionChannelFragment extends Fragment {
    private Context mContext;
     ListView list_book;
     ListView list_app;

    private ListViewAdapter<App> myAdapter1 = null;
    private ListViewAdapter<Book> myAdapter2 = null;
    private List<App> mData1 = null;
    private List<Book> mData2 = null;
    ListView mListView;
    ViewHolderAdapter mAdapter;
    ArrayList<String> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_channel_fragment, container, false);
        list_book = (ListView) view.findViewById(R.id.list_book);
        list_app = (ListView) view.findViewById(R.id.list_app);

        init();
        return view;
    }
    private void init() {

        //数据初始化
        mData1 = new ArrayList<App>();
        mData1.add(new App(R.drawable.ic_favorites,"我的题库"));
        mData1.add(new App(R.drawable.ic_recents,"充电值"));
        mData1.add(new App(R.drawable.ic_nearby,"最新热库"));
        mData1.add(new App(R.drawable.ic_nearby,"题库重置"));
        mData2 = new ArrayList<Book>();
        mData2.add(new Book("《第一行代码Android》","郭霖"));
        mData2.add(new Book("《Android群英传》","徐宜生"));
        mData2.add(new Book("《Android开发艺术探索》","任玉刚"));

        //Adapter初始化
        myAdapter1 = new ListViewAdapter<App>((ArrayList)mData1,R.layout.item_one) {
            @Override
            public void bindView(ViewHolder holder, App obj) {
                holder.setImageResource(R.id.img_icon,obj.getaIcon());
                holder.setText(R.id.txt_aname,obj.getaName());
            }
        };
        myAdapter2 = new ListViewAdapter<Book>((ArrayList)mData2,R.layout.item_two) {
            @Override
            public void bindView(ViewHolder holder, Book obj) {
                holder.setText(R.id.txt_bname,obj.getbName());
                holder.setText(R.id.txt_bauthor,obj.getbAuthor());
            }
        };

        //ListView设置下Adapter：
        list_app.setAdapter(myAdapter1);
        list_book.setAdapter(myAdapter2);
    }


}
