package com.aladdin.apps.questionbank.answers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.questions.QuestionsInteractorImpl;
import com.aladdin.apps.questionbank.questions.QuestionsPresenterImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/10/15.
 */
public class AnswersActivity extends BaseActivity implements AnswersView,
        AdapterView.OnItemClickListener,ListView.OnClickListener{
    @Bind(R.id.topToolBar)
    Toolbar topToolBar;
    @Bind(R.id.nextBankBtn)
    Button nextBankBtn;
    @Bind(R.id.answerAgainBtn)
    Button answerAgainBtn;
    @Bind(R.id.wrongAnswerAgainBtn)
    Button  wrongAnswerAgainBtn;
    @Bind(R.id.answerScore)
    TextView answerScore;
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
        //progressBar.setVisibility(View.VISIBLE);
        //fBankContentlistPager.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        //progressBar.setVisibility(View.INVISIBLE);
        //fBankContentlistPager.setVisibility(View.VISIBLE);
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

    @Override
    public void showAnswerInfo() {
        String answerInfo = "您的分数是：";
        answerInfo = answerInfo + String.valueOf(getIntent().getLongExtra("score",1L));
        answerScore.setText(answerInfo);
    }

    @Override
    public Map getFilterParamsByIntent() {
        Intent questionIntent =  getIntent();
        map = new HashMap();
        map.put("score",String.valueOf(questionIntent.getLongExtra("score",1L)));
        map.put("wrongQuestIds",questionIntent.getStringExtra("wrongQuestIds"));
        map.put("bankId",String.valueOf(questionIntent.getLongExtra("bankId",1L)));
        map.put("answerId",String.valueOf(questionIntent.getLongExtra("answerId",1L)));
        map.put("orderId",questionIntent.getStringExtra("orderId"));
        map.put("packageId",questionIntent.getStringExtra("packageId"));
        map.put("bankIds",questionIntent.getStringExtra("bankIds"));
        return map;
    }

    @Override
    public void showBottomButtons(){
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("score",String.valueOf(getIntent().getLongExtra("score",1L)));
            jsonObject.put("wrongQuestIds",getIntent().getStringExtra("wrongQuestIds"));
            jsonObject.put("oldBankId",String.valueOf(getIntent().getLongExtra("bankId",1L)));
            jsonObject.put("answerId",String.valueOf(getIntent().getLongExtra("answerId",1L)));
            jsonObject.put("orderId",getIntent().getStringExtra("orderId"));
            jsonObject.put("packageId",getIntent().getStringExtra("packageId"));
            jsonObject.put("bankIds",getIntent().getStringExtra("bankIds"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nextBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onNextBankBtnClick(view,jsonObject);
            }
        });
        answerAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAnswerAgainBtnClick(view,jsonObject);
            }
        });
        wrongAnswerAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onWrongAnswerAgainBtnClick(view,jsonObject);
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
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        presenter.onClick(view);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(parent, view, position,id);
    }
}
