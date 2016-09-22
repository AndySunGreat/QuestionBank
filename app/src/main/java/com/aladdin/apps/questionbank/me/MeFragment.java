package com.aladdin.apps.questionbank.me;

import android.support.v4.app.Fragment;
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
public class MeFragment extends Fragment {
    private Button mMeBankButton;
    private TextView mMeTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        mMeTextView = (TextView)view.findViewById(R.id.meFragTextView);
        mMeTextView.setText("This is MeBank channel");
        mMeBankButton = (Button)view.findViewById(R.id.meFragButton);
        mMeBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //你的fragment是基于fragmentactivity的，getactivity()就可以了
                Intent intent = new Intent(getActivity(),MeMainActivity.class);
                intent.putExtra("position", "test data");
                startActivity(intent);
            }
        });
        return view;
    }
}