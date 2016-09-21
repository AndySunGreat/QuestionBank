package com.aladdin.apps.questionbank.discovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/21.
 */
public class DiscoveryMainActivity extends AppCompatActivity {

    private Button mDiscoveryMainButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discovery_mainlayout);
        mDiscoveryMainButton = (Button)findViewById(R.id.discoveryMainButton);
    }
}
