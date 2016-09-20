package com.aladdin.apps.questionbank.question;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/19.
 */
public class QuestionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_fragment, container, false);
    }
}
