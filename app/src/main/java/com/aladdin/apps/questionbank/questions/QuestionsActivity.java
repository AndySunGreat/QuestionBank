package com.aladdin.apps.questionbank.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.roughike.bottombar.BottomBar;

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
    @Bind(R.id.bottomBar)
    BottomBar bottomBar;
    @Bind(R.id.submitAllQuestBtn)
    Button submitAllQuestBtn;
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
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO:   1) Display Auto Recommand mode: /package/{jobId}/auto
                    2) Display available Customize banks to customize user package: /package/{userId}/banks
        And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        setContentView(R.layout.questions_listview);
        ButterKnife.bind(this);
        //questSubmitBtn = (Button)findViewById(R.id.questSubmitBtn);
        //questionListView = (ListView)findViewById(R.id.questionsListview);
        presenter = new QuestionsPresenterImpl(this,new QuestionsInteractorImpl());
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        //bottomBar = (BottomBar) findViewById(R.id.bottomBar);
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
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        View v = getLayoutInflater().inflate(R.layout.questions_expandlistview_footview, null);
        questExpandableListView.addFooterView(v);
        QuestionAdapter adapter = new QuestionAdapter(orders,this) ;
        questExpandableListView.setAdapter(adapter);
        submitAllQuestBtn.setOnClickListener(new SubmitAllQuestion());
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

    private class SubmitAllQuestion implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            /*
            业务逻辑：
1) 当用户答题提交后，会先给用户整套题打个分值，将分值以及错题记录在下边表中，并更新order表的order status为”completed”。
Whole API: /api/bank/order/submit  [POST]

表：user_answer（用户每做完一次该套题都会插入一条记录）
API: /api/bank/order/{orderId}  [GET]--获得当前题库order
API: /api/bank/order/{orderId}/answer/{answerId} [POST] –保存用户答题情况（包括错误题以及分值）
API: /api/bank/order/{orderId} [PUT] –更新当前用户题库order的status

2) 当(1)逻辑处理完成后，会跳转到业务选择页面，该页面提供用户三种选择：
A.重新做一遍
B.进行下一环节（取决于package设置）
C.错题复习
	选择“A”，则向order表新插入一条记录（order status : new），该题库的;
WHOLE API: /api/bank/order/new
	选择“B”，则会根据order表的package Id查询package表，用户下一环节要做的题库是那一个，然后生成新的order，插入到order 表。
WHOLE API: /api/bank/order/next
	选择“C”，则会生成一条新的answer表记录，并更新order 表的change date以及将新的answer Id添加到order表的该条记录的answer Ids字段中，并更新last_answer_id。
WHOLE API: /api/bank/order/wrong
3) 当用户的该package所有题库都做完的时候，会跳转到“充电频道”的“能力评估”页面，会根据用户当前答题情况和用户所有时间、用户当前职业、用户目标进行判断，显示用户能力各项参数，以及推荐用户选择订制新的套餐。
WHOLE API: /api/charge/evaluate/{userId}

             */
            // 提交完整个答案后跳到AnswersActivity
            // navigateAnswerActivity();
        }
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
    public void navigateAnswerActivity(int position){
        /*intent = new Intent(getApplicationContext(),AnswersActivity.class);
*//*        try {
            intent.putExtra("jobId", jsonObject.getString("jobId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*//*
        Log.d("navigateAnswerActivity","navigateAnswerActivity");
        startActivity(intent);*/
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
