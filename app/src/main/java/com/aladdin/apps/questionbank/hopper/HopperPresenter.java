package com.aladdin.apps.questionbank.hopper;

import android.view.View;
import android.widget.AdapterView;

import org.json.JSONObject;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface HopperPresenter {
    void onResume();

    void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    void onDestroy();

    void onClick(View view);


    void submitSearching(JSONObject jsonObject, View v);
}
