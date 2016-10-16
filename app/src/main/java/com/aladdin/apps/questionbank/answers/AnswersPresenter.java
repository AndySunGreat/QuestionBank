package com.aladdin.apps.questionbank.answers;

import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;

import org.json.JSONObject;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface AnswersPresenter {
    void onResume();

    void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    void onDestroy();

    void onClick(View view);

    //void validateCheckedAnswer(QuestionAdapter adapter);

    //void submitAllAnswers(JSONObject jsonObject, View v);
}
