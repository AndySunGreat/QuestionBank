package com.aladdin.apps.questionbank.login.signup;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/9/19.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView,View.OnClickListener {

    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    @Bind(R.id.username_sign_up_textview)
    AutoCompleteTextView usernameTextView;
    @Bind(R.id.password_sign_up_text)
    EditText passwordEditText;
    @Bind(R.id.job_sign_up_spinner)
    Spinner jobSpinner;
    @Bind(R.id.register_form)
    View mRegisterFormView;
    @Bind(R.id.username_sign_up_form)
    View usernameSignUpView;
    @Bind(R.id.layoutRoot)
    CoordinatorLayout layoutRoot;
    @Bind(R.id.submit_sign_up_button)
    Button submitSignUpBtn;
    @Bind(R.id.sign_up_progress)
    ProgressBar progressBar;
    RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2016-10-8 ADD RegisterActivity UI components (username/pwd/job)
        setContentView(R.layout.login_c_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this,new RegisterInteractorImpl());
        submitSignUpBtn.setOnClickListener(this);
        // TODO: 2016-10-8 call /api/login/user [POST] to insert user data when click 'submit' button
        /* TODO: 2016-10-8 jump 'choose package' page to display a couple of parts
                1) Display Auto Recommand mode: /package/{jobId}/auto
                2) Display available Customize banks to customize user package: /package/{userId}/banks
                    And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        // TODO: 2016-10-8 insert first order record by calling /api/me/order[POST] when to click 'submit' in package choosing page.
        // TODO: 2016-10-8 jump 'question bank channel' and then directly go to question list pages.

    }

     @Override
     public void onClick(View v) {
         JSONObject jsonObject = new JSONObject();
         try{
             jsonObject.put("username", usernameTextView.getText().toString());
             Log.d("RegisterActivity",usernameTextView.getText().toString());
             jsonObject.put("password", passwordEditText.getText().toString());
             Log.d("RegisterActivity",passwordEditText.getText().toString());
             jsonObject.put("jobName",jobSpinner.getSelectedItem().toString());
             Log.d("RegisterActivity",jobSpinner.getSelectedItem().toString());
         }catch (JSONException e1){
            e1.printStackTrace();
         }
         presenter.onClickd(v,jsonObject);
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
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        //fBankContentlistPager.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
       // fBankContentlistPager.setVisibility(View.VISIBLE);
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
        //homeToolBar.setOnMenuItemClickListener(onMenuItemClick);
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


}
