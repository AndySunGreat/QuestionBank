package com.aladdin.apps.questionbank.packages;

import android.view.View;
import android.widget.AdapterView;

import org.json.JSONObject;


/**
 * Created by AndySun on 2016/10/8.
 */
public interface PackagesPresenter {
    void onResume();

    void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    void onDestroy();

    void onClick(View view);

}
