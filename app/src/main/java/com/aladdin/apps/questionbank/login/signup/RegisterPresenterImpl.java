package com.aladdin.apps.questionbank.login.signup;

import android.view.View;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by AndySun on 2016/10/8.
 */
public class RegisterPresenterImpl implements  RegisterPresenter, RegisterInteractor.OnCreatingUserFinishedListener {
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView registerView,
                                            RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }

    @Override
    public void onResume(){
        if (registerView != null) {
            registerView.showTitleBar();
            registerView.showProgress();
        }

    }

   @Override
    public void onClickd(View v,JSONObject jsonObject){
/*        if (registerView != null) {
            registerView.showMessage(String.format("Position %d clicked", position + 1));
        }*/
        //registerView.navigateFeaturesActivity(position);

       registerInteractor.insertUserForSignup(this,jsonObject,v.getContext());
    }

    @Override
    public void onDestroy(){
        registerView = null;
    }

    @Override
    public void onFinished(BaseResultObject items) {
        if (registerView != null) {
            //registerView.setItems(items);
            registerView.hideProgress();
        }
        // TODO: 20161009 - jump into choose package page

    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (registerView != null) {
            registerView.setItemsError(items);
            registerView.hideProgress();
        }
    }
}
