package com.aladdin.apps.questionbank.login;

import android.app.Activity;
import android.os.Bundle;

import com.aladdin.apps.questionbank.R;

/**
 * Created by AndySun on 2016/9/19.
 */
public class RegisterActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2016-10-8 ADD Register UI components (username/pwd/job)
        setContentView(R.layout.login_c_register);
        // TODO: 2016-10-8 call /api/login/user [POST] to insert user data when click 'submit' button
        /* TODO: 2016-10-8 jump 'choose package' page to display a couple of parts
                1) Display Auto Recommand mode: /package/{jobId}/auto
                2) Display available Customize banks to customize user package: /package/{userId}/banks
                    And insert this package when click 'generate' button to call API: /package/{jobId}/custom
        */
        // TODO: 2016-10-8 insert first order record by calling /api/me/order[POST] when to click 'submit' in package choosing page.
        // TODO: 2016-10-8 jump 'question bank channel' and then directly go to question list pages.

    }
}
