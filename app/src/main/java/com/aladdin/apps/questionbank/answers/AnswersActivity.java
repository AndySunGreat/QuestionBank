package com.aladdin.apps.questionbank.answers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/10/15.
 */
public class AnswersActivity extends BaseActivity implements AnswersView{
    @Bind(R.id.topToolBar)
    Toolbar topToolBar;
    private AnswersPresenter presenter;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answers_page);
        ButterKnife.bind(this);
        presenter = new AnswersPresenterImpl(this,new AnswersInteractorImpl());

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List<Question> mData) {

    }

    @Override
    public void setItemsError(BaseResultObject bro) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showTitleBar() {
        // App Logo
        //topToolBar.setLogo(R.mipmap.ic_launcher);
        String strSyncCount = "(12)";
        // Title
        topToolBar.setTitle("您的成绩" + strSyncCount);
        // Sub Title
        //topToolBar.setSubtitle("Sub title");
        setSupportActionBar(topToolBar);
        //topToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        topToolBar.setOnMenuItemClickListener(onMenuItemClick);
        // 设置回退按钮
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_favorite:
                    msg += "Click edit";
                    break;
                case R.id.action_friends:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(AnswersActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
    @Override
    public Map getFilterParamsByIntent() {
        Intent questionIntent =  getIntent();
        map.put("score",String.valueOf(questionIntent.getLongExtra("score",1L)));
        map.put("wrongQuestIds",questionIntent.getStringExtra("wrongQuestIds"));
        map.put("bankId",String.valueOf(questionIntent.getLongExtra("bankId",1L)));
        map.put("answerId",String.valueOf(questionIntent.getLongExtra("answerId",1L)));
        map.put("orderId",String.valueOf(questionIntent.getLongExtra("orderId",1L)));
        return map;
    }



}
