package com.aladdin.apps.questionbank.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.discovery.DiscoveryActivity;
import com.aladdin.apps.questionbank.me.MeActivity;
import com.aladdin.apps.questionbank.promotion.PromotionActivity;
import com.aladdin.apps.questionbank.question.QuestionActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by AndySun on 2016/9/19.
 */
public class HomeActivity extends AppCompatActivity {
    private TextView messageView;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        messageView = (TextView) findViewById(R.id.messageView);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Class clazz = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_question) {
                    messageView.setText("QuestionActivity");
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    //clazz = QuestionActivity.class;

                }else if(tabId == R.id.tab_promotion){
                    messageView.setText("PromotionActivity");
                    //clazz = PromotionActivity.class;

                }else if(tabId == R.id.tab_discovery){
                    messageView.setText("DiscoveryActivity");
                    //clazz = DiscoveryActivity.class;

                }else if(tabId == R.id.tab_me){
                    messageView.setText("MeActivity");
                    //clazz = MeActivity.class;

                }
               // startActivity(new Intent(getApplicationContext(), clazz));
            }
        });

        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_me);
        nearby.setBadgeCount(6);
    }
}
