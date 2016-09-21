package com.aladdin.apps.questionbank.discovery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.discovery.adapter.ViewpageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndySun on 2016/9/21.
 */
public class DiscoveryMainActivity extends AppCompatActivity {

    private ViewPager list_pager;

    private List<View> list_view;

    private ViewpageAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discovery_mainlayout);

        list_pager = (ViewPager)findViewById(R.id.list_pager);

        list_view = new ArrayList<>();

        //这里只设置了4.因为在实现应用中，我们在页面加载的时候，你会根据数据的多少，而知道这个页面的数量
        //一般情况下，我们会根据list<>或是string[]这样的数组的数量来判断要有多少页
        for(int i=0;i<4;i++)
        {
            View view = LayoutInflater.from(this).inflate(R.layout.discovery_fragmentpage,null);
            TextView txt_num = (TextView)view.findViewById(R.id.txt_num);
            txt_num.setText(i + "");
            list_view.add(view);
        }

        adpter = new ViewpageAdapter(list_view);
        list_pager.setAdapter(adpter);

        // 刚开始的时候 吧当前页面是先到最大值的一半 为了循环滑动
        int currentItem = Integer.MAX_VALUE / 2;
        // 让第一个当前页是 0
        //currentItem = currentItem - ((Integer.MAX_VALUE / 2) % 4);
        list_pager.setCurrentItem(currentItem);

    }
}
