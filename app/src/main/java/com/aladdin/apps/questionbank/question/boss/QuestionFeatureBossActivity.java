package com.aladdin.apps.questionbank.question.boss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aladdin.apps.questionbank.R;

import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/9/23.
 */
public class QuestionFeatureBossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_m_bank_content_listview);
        ButterKnife.bind(this);
    }
}
