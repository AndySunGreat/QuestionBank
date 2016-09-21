package com.aladdin.apps.questionbank.question;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/21.
 */
public class QuestionMainActivity extends AppCompatActivity {

    private Button questionMainButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_mainlayout);
        questionMainButton = (Button)findViewById(R.id.questionMainButton);
    }
}
