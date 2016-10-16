package com.aladdin.apps.questionbank.answers;

import android.os.Bundle;

import com.aladdin.apps.questionbank.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by AndySun on 2016/10/15.
 */
public class AnswersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
