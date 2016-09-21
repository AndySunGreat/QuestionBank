package com.aladdin.apps.questionbank.discovery;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aladdin.apps.questionbank.R;


/**
 * Created by AndySun on 2016/9/19.
 */
public class DiscoveryFragment extends Fragment {
    private Button mDiscoveryBankButton;
    private TextView mDiscoveryTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discovery_fragment, container, false);
        mDiscoveryTextView = (TextView)view.findViewById(R.id.discoveryFragTextView);
        mDiscoveryTextView.setText("This is DiscoveryBank channel");
        mDiscoveryBankButton = (Button)view.findViewById(R.id.discoveryFragButton);
        mDiscoveryBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //你的fragment是基于fragmentactivity的，getactivity()就可以了
                Intent intent = new Intent(getActivity(),DiscoveryMainActivity.class);
                intent.putExtra("position", "test data");
                startActivity(intent);
            }
        });
        return view;
    }
}
