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
import com.aladdin.apps.questionbank.answers.AnswersActivity;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.common.entity.QuestionsListViewEntity;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionItem;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionOrder;
import com.aladdin.apps.questionbank.common.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.roughike.bottombar.BottomBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/10/9.
 */
public class QuestionsActivity extends BaseActivity implements QuestionsView,
        AdapterView.OnItemClickListener,ListView.OnClickListener{
    @Bind(R.id.topToolBar)
    Toolbar topToolBar;
    @Bind(R.id.bottomBar)
    BottomBar bottomBar;
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
    private BankAnswers insertAndGetAnswer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO:   1) Display Auto Recommand mode: /package/{jobId}/auto
                    2) Display available Customize banks to customize user package: /package/{userId}/banks
        And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        setContentView(R.layout.questions_listview);
        ButterKnife.bind(this);
        insertAndGetAnswer = new BankAnswers();
        presenter = new QuestionsPresenterImpl(this,new QuestionsInteractorImpl());
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        //bottomBar = (BottomBar) findViewById(R.id.bottomBar);
    }

    @Override
    public Map getFilterParamsByIntent(){
        Intent currentIntent =  getIntent();
        // constants
        map.put("packageId",currentIntent.getStringExtra("packageId"));
        map.put("orderStatus",currentIntent.getStringExtra("orderStatus"));
        map.put("bankId",currentIntent.getStringExtra("bankId"));
        map.put("bankIds",currentIntent.getStringExtra("bankIds"));
        map.put("orderId",String.valueOf(currentIntent.getLongExtra("orderId",1L)));
        // Navigate from Package
        if("NEW".equals(currentIntent.getStringExtra("orderStatus"))){

        }else if("AGAIN".equals(currentIntent.getStringExtra("orderStatus"))){
            // 用户更新Order表answerIds
            map.put("prevAnswerId",currentIntent.getStringExtra("answerId"));
            // 用于显示错题列表
            map.put("prevWrongQuestIds",currentIntent.getStringExtra("wrongQuestIds"));
        }else if("WRONGAGAIN".equals(currentIntent.getStringExtra("orderStatus"))) {
            // 此时会传回wrongQuestIds
            map.put("prevAnswerId",currentIntent.getStringExtra("answerId"));
            // 用于显示错题列表
            map.put("prevWrongQuestIds",currentIntent.getStringExtra("wrongQuestIds"));
        }
        return map;
    }

    // 加载questions数据给question adapter.
    @Override
    public void setItems(List<Question> mData) {
        loadExpandableListView(mData);
    }

    public void loadExpandableListView(List<Question> mData){
        questExpandableListView = (ExpandableListView)findViewById(R.id.questExpandableListView);
        // Group Data
        List<QuestionOrder> orders = new ArrayList<>() ;
        // Child Data
        List<QuestionItem> questionItemList;
        // 遍历所有试题
        for (int i=0;i<mData.size();i++) {
            question = (Question)mData.get(i);
            QuestionOrder order = new QuestionOrder();
            order.setQuestionId(question.getQuestionId());
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
        Intent packageIntent =  getIntent();
        String bankId = packageIntent.getStringExtra("bankId");
        String orderId = packageIntent.getStringExtra("orderId");
       // String orderId = String.valueOf(packageIntent.getLongExtra("orderId",1L));
        Log.d("Intent orderId",orderId);

        QuestionAdapter adapter = new QuestionAdapter(orders,this) ;
        questExpandableListView.setAdapter(adapter);

        int size = adapter.getGroupCount() ;
        for (int i=0;i<size;i++) {
            questExpandableListView.expandGroup(i);
        }
        Map calculateResult = adapter.getAnswersResultMap();
        View v = getLayoutInflater().inflate(R.layout.questions_expandlistview_footview, null);
        v.findViewById(R.id.submitAllQuestBtn).setOnClickListener(new SubmitAllQuestion(calculateResult));
        questExpandableListView.addFooterView(v);

    }

    private class SubmitAllQuestion implements View.OnClickListener{
        Map calculateResult;
        SubmitAllQuestion(Map calculateResult){
            this.calculateResult = calculateResult;
        }
        @Override
        public void onClick(View view) {
            Log.d("submit all question","question");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("orderStatus",map.get("orderStatus").toString());
                jsonObject.put("bankId",map.get("bankId").toString());
                jsonObject.put("orderId",map.get("orderId"));
                jsonObject.put("packageId",map.get("packageId"));
                if(map.get("orderStatus").equals("AGAIN")){
                    jsonObject.put("prevAnswerId", map.get("prevAnswerId"));
                    jsonObject.put("prevWrongQuestIds",map.get("prevWrongQuestIds"));
                }else if(map.get("orderStatus").equals("WRONGAGAIN")){
                    jsonObject.put("prevAnswerId", map.get("prevAnswerId"));
                    jsonObject.put("prevWrongQuestIds",map.get("prevWrongQuestIds"));
                }
                // 当前用户答题结果
                jsonObject.put("wrongQuestIds",calculateResult.get("wrongQuestIds"));
                jsonObject.put("score",calculateResult.get("score"));
                map.put("wrongQuestIds",calculateResult.get("wrongQuestIds"));
                map.put("score",calculateResult.get("score"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            presenter.submitAllAnswers(jsonObject,view);
        }
    }


    // Clicking each item of list view
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(parent, view, position,id);
    }

    /**
     * Go to Question Page
     * @param updateStatusMap
     */
    @Override
    public void navigateAnswersActivity(Map updateStatusMap){
        intent = new Intent(getApplicationContext(), AnswersActivity.class);

        intent.putExtra("orderId", String.valueOf(getIntent().getLongExtra("orderId",1L)));
        intent.putExtra("packageId",getIntent().getStringExtra("packageId"));
        intent.putExtra("bankIds",getIntent().getStringExtra("bankIds"));
        intent.putExtra("orderStatus",getIntent().getStringExtra("orderStatus"));
        intent.putExtra("bankId",getIntent().getStringExtra("bankId"));
        // 将生成的answerId传到answer page
        intent.putExtra("answerId",updateStatusMap.get("answerId").toString());

        intent.putExtra("score",map.get("score").toString());
        intent.putExtra("wrongQuestIds", map.get("wrongQuestIds").toString());
        startActivity(intent);
    }

    @Override
    public void showTitleBar() {
        // App Logo
        //topToolBar.setLogo(R.mipmap.ic_launcher);
        String strSyncCount = "(12)";
        // Title
        topToolBar.setTitle("我的题库" + strSyncCount);
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

}
