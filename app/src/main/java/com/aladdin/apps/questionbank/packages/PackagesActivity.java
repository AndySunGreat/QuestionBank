package com.aladdin.apps.questionbank.packages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseActivity;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.common.entity.PackageListViewEntity;
import com.aladdin.apps.questionbank.common.listview.ListViewAdapter;
import com.aladdin.apps.questionbank.common.listview.PackageListVAdapter;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.aladdin.apps.questionbank.questions.QuestionsActivity;

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
public class PackagesActivity extends BaseActivity implements PackagesView, AdapterView.OnItemClickListener,ListView.OnClickListener{
    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    ListView packageListView;
    private ArrayAdapter<String> listAdapter ;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter myAdapter1;
    private Package autoPackage;
    private PackageListViewEntity bean;
    private PackagesPresenter presenter;
    Map map = new HashMap<>();
    private List<PackageListViewEntity> mDatas;
    private PackageListVAdapter packageListVAdapter;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO:   1) Display Auto Recommand mode: /package/{jobId}/auto
                    2) Display available Customize banks to customize user package: /package/{userId}/banks
        And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        setContentView(R.layout.packages_listview);
        ButterKnife.bind(this);
        packageListView = (ListView)findViewById(R.id.getPackageListview);
        presenter = new PackagesPresenterImpl(this,new PackagesInteractorImpl());
    }

    @Override
    public Map getFilterParams(){
        Intent registerIntent =  getIntent();
        String jobId = registerIntent.getStringExtra("jobId");
        map.put("jobId",jobId);
        return map;
    }

    @Override
    public void setItems(List<Package> mData) {
        packageListView = (ListView)findViewById(R.id.getPackageListview);
        mDatas = new ArrayList<PackageListViewEntity>();
        for(int i=0;i<mData.size();i++) {
            autoPackage = (Package) mData.get(i);
            Log.d("packageId:",String.valueOf(autoPackage.getPackageId()));
            Log.d("bankIdsJson:",autoPackage.getBankIdsJson());
            //将数据装到集合中去
            bean = new PackageListViewEntity(
                    String.valueOf(autoPackage.getPackageId()),
                    autoPackage.getBankIdsJson(),
                    autoPackage.getPackageName(), "Android为ListView和GridView打造万能适配器",
                    autoPackage.getCreateDate().toString(), "进入答题");
            mDatas.add(bean);
        }
/*        bean = new PackageListViewEntity("Android新技能2", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

        bean = new PackageListViewEntity("Android新技能3", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

        bean = new PackageListViewEntity("Android新技能4", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

        bean = new PackageListViewEntity("Android新技能4", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

        bean = new PackageListViewEntity("Android新技能4", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);

        bean = new PackageListViewEntity("Android新技能4", "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
        mDatas.add(bean);*/

        //为数据绑定适配器
        packageListVAdapter = new PackageListVAdapter(this, mDatas);
        packageListView.setAdapter(packageListVAdapter);
        // Click each item
        packageListView.setOnItemClickListener(this);
    }



    // Clicking each item of list view
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(parent, view, position,id);
    }

    /**
     * Go to Question Page
     * @param order
     */
    @Override
    public void navigateQuestionActivity(Order order){
        intent = new Intent(getApplicationContext(), QuestionsActivity.class);
        intent.putExtra("bankId", order.getBankId());
        intent.putExtra("packageId",order.getPackageId());
        intent.putExtra("orderId",String.valueOf(order.getOrderId()));
        intent.putExtra("orderStatus",order.getOrderStatus());
        String strBankIds = ((TextView)findViewById(R.id.hiddenBankIds)).getText().toString();
        Log.d("strBankIds",strBankIds);
        intent.putExtra("bankIds",strBankIds);
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
                Toast.makeText(PackagesActivity.this, msg, Toast.LENGTH_SHORT).show();
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
