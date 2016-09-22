package com.aladdin.apps.questionbank.home;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.me.MeFragment;
import com.aladdin.apps.questionbank.promotion.PromotionFragment;
import com.aladdin.apps.questionbank.discovery.DiscoveryFragment;
import com.aladdin.apps.questionbank.question.QuestionChannelFragment;
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
public class HomeActivity extends AppCompatActivity implements HomeView{

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.myCoordinator)
    CoordinatorLayout myCoordinator;
    @Bind(R.id.messageView)
    TextView messageView;

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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public void createViewPagerTab(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new QuestionChannelFragment());
        fragmentList.add(new PromotionFragment());
        fragmentList.add(new DiscoveryFragment());
        fragmentList.add(new MeFragment());
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
                    messageView.setText("QuestionActivity");
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    viewPager.setCurrentItem(0);
                }else if(tabId == R.id.tab_promotion){
                    messageView.setText("PromotionActivity");
                    viewPager.setCurrentItem(1);
                }else if(tabId == R.id.tab_discovery){
                    messageView.setText("DiscoveryFragment");
                    viewPager.setCurrentItem(2);
                }else if(tabId == R.id.tab_me){
                    messageView.setText("MeActivity");
                    viewPager.setCurrentItem(3);
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
        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_me);
        nearby.setBadgeCount(6);
        nearby.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                Toast.makeText(getApplicationContext(), "我的Tab onViewAttachedToWindow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                Toast.makeText(getApplicationContext(), "我的Tab onViewDetachedFromWindow", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    public void switchMainFragment(int location, Fragment f){
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(location,f);
//        ft.commit();
//    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_friends:
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_favorite:
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


}
