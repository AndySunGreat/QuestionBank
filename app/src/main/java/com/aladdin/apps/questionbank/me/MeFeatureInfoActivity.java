package com.aladdin.apps.questionbank.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aladdin.apps.questionbank.R;

import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/9/23.
 */
public class MeFeatureInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_m_info_content_listview);
        ButterKnife.bind(this);
    }
}
