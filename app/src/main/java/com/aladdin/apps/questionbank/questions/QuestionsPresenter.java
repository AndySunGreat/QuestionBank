package com.aladdin.apps.questionbank.questions;

import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface QuestionsPresenter {
    void onResume();

    void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    void onDestroy();

    void onClick(View view);

    void validateCheckedAnswer(QuestionAdapter adapter);
}
