package com.aladdin.apps.questionbank.home.hm;

import android.view.View;
import android.widget.AdapterView;

import org.json.JSONObject;

/**
 * Created by AndySun on 2016/10/19.
 */
public interface HomeChannelPresenter {

    void onResume();

    void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    void onDestroy();

    void onClick(View view);

}
