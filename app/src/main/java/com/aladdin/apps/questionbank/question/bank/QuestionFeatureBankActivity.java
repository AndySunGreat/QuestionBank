package com.aladdin.apps.questionbank.question.bank;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.data.bean.QuestionChooseItem;
import com.aladdin.apps.questionbank.discovery.adapter.ViewpageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/9/23.
 */
public class QuestionFeatureBankActivity extends AppCompatActivity implements QuestionFeatureBankView, AdapterView.OnItemClickListener{

    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    @Bind(R.id.fBankContentlistPager)
    ViewPager fBankContentlistPager;
    private RadioGroup radioGroup;
    private List list_view;
    private ProgressBar progressBar;
    private ViewpageAdapter adpter;
    private View view;
    private TextView quesNumberTV,quesBankNameTV,quesCategoryNameTV,quesTypeNameTV;
    private QuestionFeatureBankPresenter presenter;
    private Question question;
    private List<QuestionChooseItem> answerItemsList;
    private QuestionChooseItem questionChooseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_f_bank_content_viewpager);
        ButterKnife.bind(this);
        fBankContentlistPager = (ViewPager)findViewById(R.id.fBankContentlistPager);
        list_view = new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progress);
        presenter = new QuestionFeatureBankPresenterImpl(this, new QuestionFeatureBankInteractorImpl());
    }

    @Override
    public void setItems(List<Question> mData) {
        mData = new ArrayList<Question>();

        //这里只设置了4.因为在实现应用中，我们在页面加载的时候，你会根据数据的多少，而知道这个页面的数量
        //一般情况下，我们会根据list<>或是string[]这样的数组的数量来判断要有多少页
        for(int i=0;i<mData.size();i++)
        {
            question = (Question)mData.get(i);
            view = LayoutInflater.from(this).inflate(R.layout.ques_f_bank_content_view,null);
            quesNumberTV = (TextView)view.findViewById(R.id.quesNumberTV);
            quesBankNameTV = (TextView)view.findViewById(R.id.quesBankNameTV);
            quesCategoryNameTV = (TextView)view.findViewById(R.id.quesCategoryNameTV);
            quesTypeNameTV = (TextView)view.findViewById(R.id.quesTypeNameTV);
            quesNumberTV.setText(i + "");
            quesBankNameTV.setText(question.getQuestionBankName());
            quesCategoryNameTV.setText(question.getQuestionCategoryName());
            quesTypeNameTV.setText(question.getQuestionTypeName());
            radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
            answerItemsList = question.getAnswerItemsList();
            for(int j=0;i<answerItemsList.size();j++){
                questionChooseItem = (QuestionChooseItem)answerItemsList.get(j);
                RadioButton tempButton = new RadioButton(this);
                tempButton.setText(questionChooseItem.getQiSeq() + questionChooseItem.getQiContent());
                radioGroup.addView(tempButton);
            }
            list_view.add(view);
        }

        adpter = new ViewpageAdapter(list_view);
        fBankContentlistPager.setAdapter(adpter);

        // 刚开始的时候 吧当前页面是先到最大值的一半 为了循环滑动
        int currentItem = 0;
        //int currentItem = Integer.MAX_VALUE / 2;
        // 让第一个当前页是 0
        //currentItem = currentItem - ((Integer.MAX_VALUE / 2) % 4);
        fBankContentlistPager.setCurrentItem(currentItem);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        fBankContentlistPager.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        fBankContentlistPager.setVisibility(View.VISIBLE);
    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

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
    public void setItemsError(BaseResultObject bro) {
        showMessage(bro.getResultMsg());
    }

    @Override
    public void showTitleBar() {
        // App Logo
        homeToolBar.setLogo(R.mipmap.ic_launcher);
        String strSyncCount = "(12)";
        // Title
        homeToolBar.setTitle("我的题库" + strSyncCount);
        // Sub Title
        //homeToolBar.setSubtitle("Sub title");
        setSupportActionBar(homeToolBar);
        //homeToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        homeToolBar.setOnMenuItemClickListener(onMenuItemClick);
        // 设置回退按钮
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolBar.setNavigationOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(QuestionFeatureBankActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
}
