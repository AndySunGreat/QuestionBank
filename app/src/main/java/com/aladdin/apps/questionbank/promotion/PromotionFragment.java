package com.aladdin.apps.questionbank.promotion;

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
public class PromotionFragment extends Fragment {
    private Button mPromotionBankButton;
    private TextView mPromotionTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.promotion_fragment, container, false);
        mPromotionTextView = (TextView)view.findViewById(R.id.promotionFragTextView);
        mPromotionTextView.setText("This is PromotionBank channel");
        mPromotionBankButton = (Button)view.findViewById(R.id.promotionFragButton);
        mPromotionBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //你的fragpromotionnt是基于fragpromotionntactivity的，getactivity()就可以了
                Intent intent = new Intent(getActivity(),PromotionMainActivity.class);
                intent.putExtra("position", "test data");
                startActivity(intent);
            }
        });
        return view;
    }
}