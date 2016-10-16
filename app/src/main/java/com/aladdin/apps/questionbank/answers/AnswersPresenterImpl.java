package com.aladdin.apps.questionbank.answers;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.common.expandablelistview.QuestionAdapter;
import com.aladdin.apps.questionbank.data.bean.BankAnswers;
import com.aladdin.apps.questionbank.data.bean.Question;
import com.aladdin.apps.questionbank.questions.QuestionsInteractor;
import com.aladdin.apps.questionbank.questions.QuestionsPresenter;
import com.aladdin.apps.questionbank.questions.QuestionsView;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class AnswersPresenterImpl implements AnswersPresenter{

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {

    }
}
