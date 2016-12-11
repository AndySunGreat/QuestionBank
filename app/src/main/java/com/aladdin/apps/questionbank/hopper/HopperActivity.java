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
        hopperConditionEntity1.setConditionTitle("Test Title1");
        List<HopperConditionSubEntity> hopperConditionSubEntityList = new ArrayList<HopperConditionSubEntity>();
        HopperConditionSubEntity hopperConditionSubEntity1 = new HopperConditionSubEntity();
        hopperConditionSubEntity1.setOptSeq("optSeq1");
        hopperConditionSubEntity1.setOptContent("optContent1");
        HopperConditionSubEntity hopperConditionSubEntity2 = new HopperConditionSubEntity();
        hopperConditionSubEntity2.setOptSeq("optSeq2");
        hopperConditionSubEntity2.setOptContent("optContent2");
        hopperConditionSubEntityList.add(hopperConditionSubEntity1);
        hopperConditionSubEntityList.add(hopperConditionSubEntity2);
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
        HopperConditionEntity hopperConditionEntity1 = new HopperConditionEntity();
        hopperConditionEntity1.setConditionId(11L);
        hopperConditionEntity1.setConditionTitle("Test Title1");
        List<HopperConditionSubEntity> hopperConditionSubEntityList = new ArrayList<HopperConditionSubEntity>();
        HopperConditionSubEntity hopperConditionSubEntity1 = new HopperConditionSubEntity();
        hopperConditionSubEntity1.setOptSeq("optSeq1");
        hopperConditionSubEntity1.setOptContent("optContent1");
        HopperConditionSubEntity hopperConditionSubEntity2 = new HopperConditionSubEntity();
        hopperConditionSubEntity2.setOptSeq("optSeq2");
        hopperConditionSubEntity2.setOptContent("optContent2");
        hopperConditionSubEntityList.add(hopperConditionSubEntity1);
        hopperConditionSubEntityList.add(hopperConditionSubEntity2);
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
