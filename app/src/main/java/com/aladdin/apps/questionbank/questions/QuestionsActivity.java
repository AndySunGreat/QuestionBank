package com.aladdin.apps.questionbank.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.common.entity.QuestionsListViewEntity;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionItem;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionOrder;
import com.aladdin.apps.questionbank.common.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.data.bean.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/10/9.
 */
public class QuestionsActivity extends BaseActivity implements QuestionsView, AdapterView.OnItemClickListener,ListView.OnClickListener{
    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    @Bind(R.id.questSubmitBtn)
    Button questSubmitBtn;
    ListView questionListView;
    ExpandableListView questExpandableListView;
    private ArrayAdapter<String> listAdapter ;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter myAdapter1;
    private Question question;
    private QuestionsListViewEntity bean;
    private QuestionsPresenter presenter;
    Map map = new HashMap<>();
    private List<QuestionsListViewEntity> mDatas;
    //private QuestionsListVAdapter questionListVAdapter;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO:   1) Display Auto Recommand mode: /package/{jobId}/auto
                    2) Display available Customize banks to customize user package: /package/{userId}/banks
        And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        setContentView(R.layout.questions_listview);
        ButterKnife.bind(this);
        questSubmitBtn = (Button)findViewById(R.id.questSubmitBtn);
        //questionListView = (ListView)findViewById(R.id.questionsListview);
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        presenter = new QuestionsPresenterImpl(this,new QuestionsInteractorImpl());
    }

    @Override
    public Map getFilterParams(){
        Intent registerIntent =  getIntent();
        String bankId = registerIntent.getStringExtra("bankId");
        map.put("bankId",bankId);
        return map;
    }

    @Override
    public void setItems(List<Question> mData) {
        //loadListViewData(mData);
        loadExpandableListView(mData);
    }

    public void loadExpandableListView(List<Question> mData){
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        // Group Data
        List<QuestionOrder> orders = new ArrayList<>() ;
        // Child Data
        List<QuestionItem> questionItemList = new ArrayList<>();
        // 遍历所有试题
        for (int i=0;i<mData.size();i++) {
            question = (Question)mData.get(i);
            QuestionOrder order = new QuestionOrder();
            // 设置题目标题
            order.setQuestTitle("["+question.getQuestType().toString() + "]    " + question.getQuestContent().toString());
            //questOptJson = question.getQuestOptionsJson();
            questionItemList = question.getQuestOptions();
            order.setItems(questionItemList);
            order.setCorrectAnswer(question.getCorrectAnswer());
            order.setCorrectPostions(question.getCorrectIndexes());
            order.setQuestionType(question.getQuestType());
            orders.add(order);
        }

        QuestionAdapter adapter = new QuestionAdapter(orders,this) ;
        questExpandableListView.setAdapter(adapter);

        //questExpandableListView.setOnItemClickListener(this);

        int size = adapter.getGroupCount() ;
        for (int i=0;i<size;i++) {
            questExpandableListView.expandGroup(i);
        }
    }

    @Override
    public void showCorrectAnswer(){
        QuestionAdapter adapter = (QuestionAdapter) questExpandableListView.getAdapter();
        presenter.validateCheckedAnswer(adapter);
    }



    // Clicking each item of list view
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(parent, view, position,id);
    }

    /**
     * Go to Question Page
     * @param position
     */
    @Override
    public void navigateQuestionActivity(int position){
        intent = new Intent(getApplicationContext(), QuestionsActivity.class);
/*        try {
            intent.putExtra("jobId", jsonObject.getString("jobId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        Log.d("navigatePackageActivity","navigatePackageActivity");
        startActivity(intent);
    }


    @Override
    public void showTitleBar() {
        // App Logo
        //homeToolBar.setLogo(R.mipmap.ic_launcher);
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
                Toast.makeText(QuestionsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


    @Override
    public void setItemsError(BaseResultObject bro) {
        showMessage(bro.getResultMsg());
    }



    @Override
    public void onClick(View view) {
        presenter.onClick(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
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
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public void loadListViewData(List<Question> mData){
/*       questionListView = (ListView)findViewById(R.id.questionsListview);
        mDatas = new ArrayList<QuestionsListViewEntity>();
        for(int i=0;i<mData.size();i++) {
            question = (Question) mData.get(i);
            //将数据装到集合中去
            bean = new QuestionsListViewEntity(question.getQuestContent(), question.getQuestOptionsJson(),
                    question.getChangeDate(), "进入答题");
            mDatas.add(bean);
        }
*//*        bean = new QuestionsListViewEntity("Android新技能2", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

*//*
        //为数据绑定适配器
        questionListVAdapter = new QuestionsListVAdapter(this, mDatas);
        questionListView.setAdapter(questionListVAdapter);
        // Click each item
        questionListView.setOnItemClickListener(this);*/
    }

}
