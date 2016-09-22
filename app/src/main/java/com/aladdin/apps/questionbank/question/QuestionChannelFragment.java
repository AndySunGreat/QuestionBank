package com.aladdin.apps.questionbank.question;

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
public class QuestionChannelFragment extends Fragment {
    private Button mQuestionBankButton;
    private TextView mQuestionTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_mainchannel_fragment, container, false);
        mQuestionTextView = (TextView)view.findViewById(R.id.questionFragTextView);
        mQuestionTextView.setText("This is QuestionBank channel");
        mQuestionBankButton = (Button)view.findViewById(R.id.questionFragButton);
        mQuestionBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //你的fragment是基于fragmentactivity的，getactivity()就可以了
                Intent intent = new Intent(getActivity(),QuestionMainActivity.class);
                intent.putExtra("position", "test data");
                startActivity(intent);
            }
        });
        return view;
    }
}
