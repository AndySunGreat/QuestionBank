package com.aladdin.apps.questionbank.question;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.apps.questionbank.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndySun on 2016/9/21.
 */
public class QuestionMainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private List<View> mViews=new ArrayList<View>();//定义用于存放Tab对应的View，你也可以用其他的集合来存放，List用得比较多
    private List<String> mTitle=new ArrayList<String>();//用于存放Tab标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_mainpage);
        init();//一系列初始化
    }
    //找到对应的View
    private void getViews(){
        mViewPager= (ViewPager) findViewById(R.id.question_main_container);
        mPagerTabStrip= (PagerTabStrip) findViewById(R.id.question_main_tabstrip);
    }
    private void initTab(){
        mPagerTabStrip.setDrawFullUnderline(false);
        mPagerTabStrip.setBackgroundColor(Color.parseColor("#2b2b2b"));
        //设置当前tab页签
        mPagerTabStrip.setTextColor(Color.parseColor("#236f28"));
        mPagerTabStrip.setTabIndicatorColor(Color.parseColor("#bc6e1c"));
    }
    //开始添加view到List中
    private void addViewToTab(){
        View view = LayoutInflater.from(QuestionMainActivity.this).inflate(R.layout.question_sublayout_home, null);
        mViews.add(view);
        view = LayoutInflater.from(this).inflate(R.layout.question_sublayout_discovery, null);
        mViews.add(view);
        view = LayoutInflater.from(this).inflate(R.layout.question_sublayout_me, null);
        mViews.add(view);
        //页签项标题
        mTitle.add("主界面");
        mTitle.add("发现");
        mTitle.add("我");
    }
    private void init(){
        getViews();
        initTab();
        addViewToTab();
        setAdapter();
    }
    private void setAdapter(){
        mViewPager.setAdapter(new GuidePagerAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("TAG","position:"+position+"  positionOffset:"+positionOffset+"  positionOffsetPixels:"+positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                Log.d("TAG","CurrentPageIndex:" +position);
            }
            //state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("TAG","State:"+state);
            }
        });
    }
    private class GuidePagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d("TAG","Page View init");
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d("TAG","ITEM DESTROY");
            container.removeView(mViews.get(position));
        }

        @Override
        public int getCount() {
            return mViews!=null ? mViews.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        //如果使用了PagerTabStrip作为滑块，一定要记得重写这个方法，这个方法就是返回Tab的标题的
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
