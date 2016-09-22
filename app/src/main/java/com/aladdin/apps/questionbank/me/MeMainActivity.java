package com.aladdin.apps.questionbank.me;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aladdin.apps.questionbank.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/9/21.
 */
public class MeMainActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_mainlayout);
        ButterKnife.bind(this);
    }
}
