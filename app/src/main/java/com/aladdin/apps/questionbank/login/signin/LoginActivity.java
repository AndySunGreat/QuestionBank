package com.aladdin.apps.questionbank.login.signin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.home.HomeActivity;
import com.aladdin.apps.questionbank.login.signup.RegisterActivity;
import com.aladdin.apps.questionbank.util.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
   // private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CoordinatorLayout layoutRoot;
    private FloatingActionButton btnFab;
    @Bind(R.id.homeToolBar)
    Toolbar homeToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_c_signin);
        ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        showTitleBar();
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        layoutRoot = (CoordinatorLayout) findViewById(R.id.layoutRoot);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
/*        if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        //mEmailView.setText("andysungj@126.com");
        //mPasswordView.setText("guanjiang");
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            RequestParams params1= new RequestParams();
            params1.put("accountNumber",email);
            params1.put("password",password);
            try {
                asynRESTForLogin(params1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
/*
 comment by abbey
 mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);*/
        }
    }


    public void showTitleBar() {
        // App Logo
        //topToolBar.setLogo(R.mipmap.ic_launcher);
        // String strSyncCount = "(12)";
        String strSyncCount = "";
        // Title
        homeToolBar.setTitle("充电宝" + strSyncCount);
        // Sub Title
        //topToolBar.setSubtitle("Sub title");
        setSupportActionBar(homeToolBar);
        //topToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        homeToolBar.setOnMenuItemClickListener(onMenuItemClick);
        // 设置回退按钮
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*       homeToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_favorite:
                    msg += "Click edit";
                    break;
                case R.id.action_friends:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        if (VERSION.SDK_INT >= 14) {
            // Use ContactsContract.Profile (API 14+)
            getLoaderManager().initLoader(0, null, this);
        } else if (VERSION.SDK_INT >= 8) {
            // Use AccountManager (API 8+)
            new SetupEmailAutoCompleteTask().execute(null, null);
        }
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }




    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Use an AsyncTask to fetch the user's email addresses on a background thread, and update
     * the email text field with results on the main UI thread.
     */
    class SetupEmailAutoCompleteTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            ArrayList<String> emailAddressCollection = new ArrayList<>();

            // Get all emails from the user's contacts and copy them to a list.
            ContentResolver cr = getContentResolver();
            Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    null, null, null);
            while (emailCur.moveToNext()) {
                String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract
                        .CommonDataKinds.Email.DATA));
                emailAddressCollection.add(email);
            }
            emailCur.close();

            return emailAddressCollection;
        }

        @Override
        protected void onPostExecute(List<String> emailAddressCollection) {
            addEmailsToAutoComplete(emailAddressCollection);
        }
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void asynRESTForLogin(RequestParams params) throws JSONException{
        // Show Progress Dialog
        //progress.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Constants.restfulEndpoints + Constants.getLoginByAccountNumberUrl;
        Log.d("LoginActivity-url",url);
        Snackbar.make(layoutRoot, "发送请求到服务器1...", Snackbar.LENGTH_SHORT)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform anything for the action selected
                    }
                })
                .show();
        client.get(url, params,new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject responseObj) {
                        super.onSuccess(statusCode, headers, responseObj);
                        Log.d("LoginActivity",responseObj.toString());
                         String strObj = responseObj.toString();
                        // 200B - No UserNew found for AccountNumber
                        if(strObj.indexOf("200C")!=-1){
                            // 200C - Password is incorrect
                            // TODO: 2016-10-18 handle password incorrect logic
                            Log.d("LoginActivity","200c");
                        }else{
                            Snackbar.make(layoutRoot, "登录成功!", Snackbar.LENGTH_SHORT)
                                    .setAction("Undo", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Perform anything for the action selected
                                        }
                                    })
                                    .show();
                            navigatetoHomeActivity();
                        }
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        // called when response HTTP status is "200 OK"
                        //progress.hide();
                        if (statusCode == 200) {
                            // 存储数组变量
                            List<String> objects = new ArrayList<String>();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // 获取具体的一个JSONObject对象
                                    JSONObject obj = response.getJSONObject(i);
                                    Toast.makeText(getApplicationContext(),  "afdsf", Toast.LENGTH_LONG).show();
                                    Log.i("QuestionId:", obj.getInt("questionId") + "");
                                    objects.add(obj.getString("questionSubject"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        //Navigate to Home Screen
                        navigatetoHomeActivity();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String content,
                                          Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        // Hide Progress Dialog
                        //progress.hide();
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                            navigatetoRegisterActivity();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                        }
                    }
            public void navigatetoHomeActivity(){
                Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

            public void navigatetoRegisterActivity(){
                Intent registerIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registerIntent);
            }

        });
    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
/**public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            //register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                //finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
 */

}

