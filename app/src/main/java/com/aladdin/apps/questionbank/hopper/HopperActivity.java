package com.aladdin.apps.questionbank.hopper;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperConditionAdapter;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperConditionEntity;
import com.aladdin.apps.questionbank.common.expandablelistview.HopperConditionSubEntity;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.roughike.bottombar.BottomBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/12/7.
 */
public class HopperActivity extends BaseActivity implements HopperView,
        AdapterView.OnItemClickListener,ListView.OnClickListener {
    @Bind(R.id.topToolBar)
    Toolbar topToolBar;
    @Bind(R.id.bottomBar)
    BottomBar bottomBar;

    ExpandableListView hopperReasonExpListView;
    private HopperPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO:   1) Display Auto Recommand mode: /package/{jobId}/auto
                    2) Display available Customize banks to customize user package: /package/{userId}/banks
        And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        setContentView(R.layout.hopper_expandlistview);
        ButterKnife.bind(this);
        //insertAndGetAnswer = new BankAnswers();
        presenter = new HopperPresenterImpl(this,new HopperInteractorImpl());
        hopperReasonExpListView = (ExpandableListView)findViewById(R.id.hopperReasonExpListView);
        //bottomBar = (BottomBar) findViewById(R.id.bottomBar);
    }

    /**
     * 加载hopper reason data into hopper adapter
     * @param mData
     */
    @Override
    public void setItems(List<Question> mData) {
        if(hopperReasonExpListView==null){
            hopperReasonExpListView = (ExpandableListView)findViewById(R.id.hopperReasonExpListView);
        }

        List<HopperConditionEntity> orders = new ArrayList<>() ;
        HopperConditionEntity hopperConditionEntity1 = new HopperConditionEntity();
        hopperConditionEntity1.setConditionId(11L);
        hopperConditionEntity1.setConditionTitle("请选择什么原因跳槽？");
        List<HopperConditionSubEntity> hopperConditionSubEntityList = new ArrayList<HopperConditionSubEntity>();
        HopperConditionSubEntity hopperConditionSubEntity1 = new HopperConditionSubEntity();
        hopperConditionSubEntity1.setOptSeq("1)");
        hopperConditionSubEntity1.setOptContent("钱少");
        HopperConditionSubEntity hopperConditionSubEntity2 = new HopperConditionSubEntity();
        hopperConditionSubEntity2.setOptSeq("2)");
        hopperConditionSubEntity2.setOptContent("活多，总加班");
        HopperConditionSubEntity hopperConditionSubEntity3 = new HopperConditionSubEntity();
        hopperConditionSubEntity3.setOptSeq("3)");
        hopperConditionSubEntity3.setOptContent("总出差，与家人聚少离多");
        HopperConditionSubEntity hopperConditionSubEntity4 = new HopperConditionSubEntity();
        hopperConditionSubEntity4.setOptSeq("4)");
        hopperConditionSubEntity4.setOptContent("单位离家远");
        HopperConditionSubEntity hopperConditionSubEntity5 = new HopperConditionSubEntity();
        hopperConditionSubEntity5.setOptSeq("5)");
        hopperConditionSubEntity5.setOptContent("发展受限，没有学习进步的机会");
        HopperConditionSubEntity hopperConditionSubEntity6 = new HopperConditionSubEntity();
        hopperConditionSubEntity6.setOptSeq("6)");
        hopperConditionSubEntity6.setOptContent("人际关系复杂，人浮于世");
        HopperConditionSubEntity hopperConditionSubEntity7 = new HopperConditionSubEntity();
        hopperConditionSubEntity7.setOptSeq("7)");
        hopperConditionSubEntity7.setOptContent("公司都是论资排辈");
        HopperConditionSubEntity hopperConditionSubEntity8 = new HopperConditionSubEntity();
        hopperConditionSubEntity8.setOptSeq("8)");
        hopperConditionSubEntity8.setOptContent("想换一个行业发展");
        hopperConditionSubEntityList.add(hopperConditionSubEntity1);
        hopperConditionSubEntityList.add(hopperConditionSubEntity2);
        hopperConditionSubEntityList.add(hopperConditionSubEntity3);
        hopperConditionSubEntityList.add(hopperConditionSubEntity4);
        hopperConditionSubEntityList.add(hopperConditionSubEntity5);
        hopperConditionSubEntityList.add(hopperConditionSubEntity6);
        hopperConditionSubEntityList.add(hopperConditionSubEntity7);
        hopperConditionSubEntityList.add(hopperConditionSubEntity8);


        //hopperConditionSubEntityList
        hopperConditionEntity1.setItems(hopperConditionSubEntityList);
        orders.add(hopperConditionEntity1);

        HopperConditionAdapter hopperConditionAdapter = new HopperConditionAdapter(orders,this);
        hopperReasonExpListView.setAdapter(hopperConditionAdapter);
        int size = hopperConditionAdapter.getGroupCount();
        for(int i=0;i<size;i++){
            hopperReasonExpListView.expandGroup(i);
        }
        // footer section
        View v = getLayoutInflater().inflate(R.layout.hopper_expandlistview_footview,null);
        v.findViewById(R.id.submitSearchingBtn).setOnClickListener(new SubmitSearching(hopperConditionAdapter));
        hopperReasonExpListView.addFooterView(v);
    }

    private class SubmitSearching implements View.OnClickListener{
        HopperConditionAdapter adapter;
        public SubmitSearching(HopperConditionAdapter adapter){
            this.adapter = adapter;
        };
        @Override
        public void onClick(View view) {
            JSONObject jsonObject = new JSONObject();
            presenter.submitSearching(jsonObject,view);
        }
    }

    @Override
    public void navigateAnswersActivity(Map map) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    public void loadData(){
        if(hopperReasonExpListView==null){
            hopperReasonExpListView = (ExpandableListView)findViewById(R.id.hopperReasonExpListView);
        }

        List<HopperConditionEntity> orders = new ArrayList<>() ;
        HopperConditionEntity hopperConditionEntity2 = new HopperConditionEntity();
        hopperConditionEntity2.setConditionId(12L);
        hopperConditionEntity2.setConditionTitle("请选择您的当前月薪？");
        hopperConditionEntity2.setGroupTitle("当前月薪");
        List<HopperConditionSubEntity> hopperConditionSubEntityList2 = new ArrayList<HopperConditionSubEntity>();
        HopperConditionSubEntity hopperConditionSubEntitySalary = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary.setOptSeq("1)");
        hopperConditionSubEntitySalary.setOptContent("0-1000");
        HopperConditionSubEntity hopperConditionSubEntitySalary1 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary1.setOptSeq("2)");
        hopperConditionSubEntitySalary1.setOptContent("1000-2000");
        HopperConditionSubEntity hopperConditionSubEntitySalary2 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary2.setOptSeq("3)");
        hopperConditionSubEntitySalary2.setOptContent("2000-3000");
        HopperConditionSubEntity hopperConditionSubEntitySalary3 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary3.setOptSeq("4)");
        hopperConditionSubEntitySalary3.setOptContent("3000-4000");
        HopperConditionSubEntity hopperConditionSubEntitySalary4 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary4.setOptSeq("5)");
        hopperConditionSubEntitySalary4.setOptContent("4000-5000");
        HopperConditionSubEntity hopperConditionSubEntitySalary5 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary5.setOptSeq("6)");
        hopperConditionSubEntitySalary5.setOptContent("5000-6000");
        HopperConditionSubEntity hopperConditionSubEntitySalary6 = new HopperConditionSubEntity();
        hopperConditionSubEntitySalary6.setOptSeq("7)");
        hopperConditionSubEntitySalary6.setOptContent("5000-6000");
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary1);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary2);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary3);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary4);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary5);
        hopperConditionSubEntityList2.add(hopperConditionSubEntitySalary6);
        hopperConditionEntity2.setItems(hopperConditionSubEntityList2);

        HopperConditionEntity hopperConditionEntity1 = new HopperConditionEntity();
        hopperConditionEntity1.setConditionId(11L);
        hopperConditionEntity1.setConditionTitle("请选择什么原因跳槽？");
        hopperConditionEntity1.setGroupTitle("跳槽目标");
        List<HopperConditionSubEntity> hopperConditionSubEntityList = new ArrayList<HopperConditionSubEntity>();

        HopperConditionSubEntity hopperConditionSubEntity1 = new HopperConditionSubEntity();
        hopperConditionSubEntity1.setOptSeq("1)");
        hopperConditionSubEntity1.setOptContent("钱少");
        HopperConditionSubEntity hopperConditionSubEntity2 = new HopperConditionSubEntity();
        hopperConditionSubEntity2.setOptSeq("2)");
        hopperConditionSubEntity2.setOptContent("活多，总加班");
        HopperConditionSubEntity hopperConditionSubEntity3 = new HopperConditionSubEntity();
        hopperConditionSubEntity3.setOptSeq("3)");
        hopperConditionSubEntity3.setOptContent("总出差，与家人聚少离多");
        HopperConditionSubEntity hopperConditionSubEntity4 = new HopperConditionSubEntity();
        hopperConditionSubEntity4.setOptSeq("4)");
        hopperConditionSubEntity4.setOptContent("单位离家远");
        HopperConditionSubEntity hopperConditionSubEntity5 = new HopperConditionSubEntity();
        hopperConditionSubEntity5.setOptSeq("5)");
        hopperConditionSubEntity5.setOptContent("发展受限，没有学习进步的机会");
        HopperConditionSubEntity hopperConditionSubEntity6 = new HopperConditionSubEntity();
        hopperConditionSubEntity6.setOptSeq("6)");
        hopperConditionSubEntity6.setOptContent("人际关系复杂，人浮于世");
        HopperConditionSubEntity hopperConditionSubEntity7 = new HopperConditionSubEntity();
        hopperConditionSubEntity7.setOptSeq("7)");
        hopperConditionSubEntity7.setOptContent("公司都是论资排辈");
        HopperConditionSubEntity hopperConditionSubEntity8 = new HopperConditionSubEntity();
        hopperConditionSubEntity8.setOptSeq("8)");
        hopperConditionSubEntity8.setOptContent("想换一个行业发展");
        hopperConditionSubEntityList.add(hopperConditionSubEntity1);
        hopperConditionSubEntityList.add(hopperConditionSubEntity2);
        hopperConditionSubEntityList.add(hopperConditionSubEntity3);
        hopperConditionSubEntityList.add(hopperConditionSubEntity4);
        hopperConditionSubEntityList.add(hopperConditionSubEntity5);
        hopperConditionSubEntityList.add(hopperConditionSubEntity6);
        hopperConditionSubEntityList.add(hopperConditionSubEntity7);
        hopperConditionSubEntityList.add(hopperConditionSubEntity8);
        //hopperConditionSubEntityList
        hopperConditionEntity1.setItems(hopperConditionSubEntityList);
        orders.add(hopperConditionEntity1);
        orders.add(hopperConditionEntity2);

        HopperConditionAdapter hopperConditionAdapter = new HopperConditionAdapter(orders,this);
        hopperReasonExpListView.setAdapter(hopperConditionAdapter);
        int size = hopperConditionAdapter.getGroupCount();
        for(int i=0;i<size;i++){
            hopperReasonExpListView.expandGroup(i);
        }
        // footer section
        View v = getLayoutInflater().inflate(R.layout.hopper_expandlistview_footview,null);
        v.findViewById(R.id.submitSearchingBtn).setOnClickListener(new SubmitSearching(hopperConditionAdapter));
        hopperReasonExpListView.addFooterView(v);
    }



    @Override
    protected void onResume() {
        loadData();
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
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTitleBar() {
        // App Logo
        //topToolBar.setLogo(R.mipmap.ic_launcher);
        String strSyncCount = "(12)";
        // Title
        topToolBar.setTitle("我要跳槽" + strSyncCount);
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
                Toast.makeText(HopperActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public Map getFilterParamsByIntent() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(parent, view, position,id);
    }
}
