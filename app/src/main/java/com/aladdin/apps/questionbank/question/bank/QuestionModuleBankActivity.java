package com.aladdin.apps.questionbank.question.bank;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.base.ChannelRow;
import com.aladdin.apps.questionbank.component.listview.ListViewAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.client.cache.Resource;

/**
 * Created by AndySun on 2016/9/21.
 */
public class QuestionModuleBankActivity extends AppCompatActivity implements QuestionModuleBankView, AdapterView.OnItemClickListener {

    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    private ListView mainListView;
    private ProgressBar progressBar;
    private ArrayList<ChannelRow> mData1;
    private ListViewAdapter myAdapter1;
    private QuestionModuleBankPresenter presenter;
    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_m_bank_content_listview);
        ButterKnife.bind(this);
        mainListView = (ListView) findViewById(R.id.quesmBankContentListview);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        mainListView.setOnItemClickListener(this);
        presenter = new QuestionModuleBankPresenterImpl(this, new QuestionModuleBankInteractorImpl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
         * if it is present.
         */
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        mainListView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        mainListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<ChannelRow> mData) {
        //mData1 = new ArrayList<ChannelRow>();
        //Adapter初始化
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData, R.layout.ques_m_bank_content_listview_row) {
            @Override
            public void bindView(ViewHolder holder, ChannelRow obj) {
                int resID = getResources().getIdentifier(obj.getRowIconImgName().toString(),
                        "drawable", "com.aladdin.apps.questionbank");
                Drawable image = getResources().getDrawable(resID);
                holder.setImageResource(R.id.rowLogo,resID);
                holder.setText(R.id.rowName, obj.getRowName());
                /*为Button添加点击事件*/
            /*    holder.bt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("BaseAdapterTest", "你点击了按钮" + position);
                        //打印Button的点击信息
                    }
                });*/
            }
        };
        mainListView.setAdapter(myAdapter1);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

    @Override
    public void navigateFeaturesActivity(int position) {
        intent = new Intent(getApplicationContext(), QuestionFeatureBankActivity.class);
        intent.putExtra("userLevel", "Senior Software Engineer");
        if (position == 0) {
            intent.putExtra("industry", "IT");
            intent.putExtra("questionCategory", "Java");
            intent.putExtra("questionBankName", "JavaCore");
        } else if (position == 1) {
            intent.putExtra("industry", "IT");
            intent.putExtra("questionCategory", "Java");
            intent.putExtra("questionBankName", "SpringMVC");
        }
        startActivity(intent);
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

    private void createQuesBankModuleContent() {
        //数据初始化
        mData1 = new ArrayList<ChannelRow>();
        mData1.add(new ChannelRow(R.drawable.ic_favorites, "Java Core"));
        mData1.add(new ChannelRow(R.drawable.ic_recents, "Spring MVC"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "Javascript"));
        mData1.add(new ChannelRow(R.drawable.ic_restaurants, "HTML/CSS"));
        mData1.add(new ChannelRow(R.drawable.ic_nearby, "Java Thread"));
        //Adapter初始化
        myAdapter1 = new ListViewAdapter<ChannelRow>((ArrayList) mData1, R.layout.ques_m_bank_content_listview_row) {
            @Override
            public void bindView(ViewHolder holder, ChannelRow obj) {
                holder.setImageResource(R.id.rowLogo, obj.getRowIcon());
                holder.setText(R.id.rowName, obj.getRowName());
                /*为Button添加点击事件*/
            /*    holder.bt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("BaseAdapterTest", "你点击了按钮" + position);
                        //打印Button的点击信息
                    }
                });*/
            }
        };
        mainListView.setAdapter(myAdapter1);
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
                Toast.makeText(QuestionModuleBankActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
