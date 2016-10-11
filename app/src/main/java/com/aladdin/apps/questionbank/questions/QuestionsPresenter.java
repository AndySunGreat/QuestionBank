package com.aladdin.apps.questionbank.questions;

import android.view.View;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface QuestionsPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();

    void onClick(View view);

}
