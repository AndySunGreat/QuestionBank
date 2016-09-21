package com.aladdin.apps.questionbank.promotion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/21.
 */
public class PromotionMainActivity extends AppCompatActivity {

    private Button mPromotionMainButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_mainlayout);
        mPromotionMainButton = (Button)findViewById(R.id.promotionMainButton);
    }
}
