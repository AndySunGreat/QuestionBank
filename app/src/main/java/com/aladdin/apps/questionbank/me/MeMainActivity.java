package com.aladdin.apps.questionbank.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/21.
 */
public class MeMainActivity extends AppCompatActivity {

    private Button mMeMainButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_mainlayout);
        mMeMainButton = (Button)findViewById(R.id.meMainButton);
    }
}
