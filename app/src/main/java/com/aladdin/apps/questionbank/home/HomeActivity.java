package com.aladdin.apps.questionbank.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.home.hm.HomeChannelFragment;
import com.aladdin.apps.questionbank.home.my.MyChannelFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by AndySun on 2016/9/19.
 */
public class HomeActivity extends AppCompatActivity implements HomeView {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.myCoordinator)
    CoordinatorLayout myCoordinator;
    //@Bind(R.id.messageView)
    //TextView messageView;
    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;

    @Bind(R.id.bottomBar)
    BottomBar bottomBar;

    private List<Fragment> fragmentList;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);
        createTitleBar();
        createViewPagerTab();
        createBottomBar();
        asyncBadgeView();  // 相当于微信小红点
    }

    @Override
    public void createTitleBar(){
        // App Logo
        //homeToolBar.setLogo(R.mipmap.ic_launcher);
        //String strSyncCount = "(11)";
        String strSyncCount = "";
        // Title
        homeToolBar.setTitle("充电宝"+strSyncCount);
        // Sub Title
        //homeToolBar.setSubtitle("Sub title");
        setSupportActionBar(homeToolBar);
        //homeToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        homeToolBar.setOnMenuItemClickListener(onMenuItemClick);
        // 设置回退按钮
        //关键下面两句话，设置了回退按钮，及点击事件的效果
/*       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
         * if it is present.
         */
        //getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        //return true;
        return false;
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
/*                case R.id.action_favorite:
                    msg += "Click edit";
                    break;
                case R.id.action_friends:
                    msg += "Click share";
                    break;*/
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public void createViewPagerTab(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeChannelFragment());
        fragmentList.add(new MyChannelFragment());
        //fragmentList.add(new PromotionFragment());
        //fragmentList.add(new DiscoveryFragment());
        //fragmentList.add(new MeChannelFragment());
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void createBottomBar(){
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment =null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_question) {
                    Toast.makeText(getApplicationContext(), "Question", Toast.LENGTH_SHORT).show();
                    //messageView.setText("QuestionActivity");
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    viewPager.setCurrentItem(0);
                }else if(tabId == R.id.tab_me){
                    //messageView.setText("PromotionActivity");
                    viewPager.setCurrentItem(1);
                }else if(tabId == R.id.tab_promotion){
                    //messageView.setText("DiscoveryFragment");
                    viewPager.setCurrentItem(2);
                }
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_question) {
                    Toast.makeText(getApplicationContext(), "Reselection Question", Toast.LENGTH_SHORT).show();
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                }
            }
        });

    }

    // 创建BadgeView
    public void asyncBadgeView(){
        BottomBarTab homeTab = bottomBar.getTabWithId(R.id.tab_question);
        //homeTab.getTitleTextAppearance();
        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_me);
        //nearby.setBadgeCount(6);
        nearby.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                //Toast.makeText(getApplicationContext(), "我的Tab onViewAttachedToWindow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                //Toast.makeText(getApplicationContext(), "我的Tab onViewDetachedFromWindow", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
