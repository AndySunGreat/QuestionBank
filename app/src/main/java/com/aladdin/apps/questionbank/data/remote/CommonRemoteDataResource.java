package com.aladdin.apps.questionbank.data.remote;

/**
 * Created by AndySun on 2016/9/24.
 */
public class CommonRemoteDataResource {


    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
 /*   public void asynRESTForLogin(RequestParams params) throws JSONException {
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
                if(strObj.indexOf("200B")!=-1){
                    navigatetoRegisterActivity();
                }else if(strObj.indexOf("200C")!=-1){
                    //TODO CLEAN INPUTS
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
                            // TODO Auto-generated catch block
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
    }*/
}
