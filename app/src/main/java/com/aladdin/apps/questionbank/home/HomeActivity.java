package com.aladdin.apps.questionbank.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.me.MeFragment;
import com.aladdin.apps.questionbank.promotion.PromotionFragment;
import com.aladdin.apps.questionbank.discovery.DiscoveryFragment;
import com.aladdin.apps.questionbank.question.QuestionFragment;
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
            Fragment fragment =null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_question) {
                    Toast.makeText(getApplicationContext(), "Question", Toast.LENGTH_SHORT).show();
                    messageView.setText("QuestionActivity");
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    fragment = new QuestionFragment();

                }else if(tabId == R.id.tab_promotion){
                    messageView.setText("PromotionActivity");
                    fragment = new PromotionFragment();
                }else if(tabId == R.id.tab_discovery){
                    messageView.setText("DiscoveryFragment");
                    fragment = new DiscoveryFragment();

                }else if(tabId == R.id.tab_me){
                    messageView.setText("MeActivity");
                    fragment = new MeFragment();
                }
                switchMainFragment(R.id.contentContainer,fragment);
            }
        });

        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_me);
        nearby.setBadgeCount(6);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }
    public void switchMainFragment(int location, Fragment f){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(location,f);
        ft.commit();
    }

    @Override
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
    }


}
