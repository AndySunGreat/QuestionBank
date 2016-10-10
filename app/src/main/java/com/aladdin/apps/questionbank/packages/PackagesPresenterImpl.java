package com.aladdin.apps.questionbank.packages;

import android.app.DownloadManager;
import android.content.Intent;
import android.util.Log;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Package;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/9/24.
 */
public class PackagesPresenterImpl implements PackagesPresenter, PackagesInteractor.OnShowingPackagesFinishedListener{

    private PackagesView packagesView;
    private PackagesInteractor packagesInteractor;
    private Map map;
    public PackagesPresenterImpl(PackagesView packagesView,
                                 PackagesInteractor packagesInteractor) {
        this.packagesView = packagesView;
        this.packagesInteractor = packagesInteractor;
    }

    @Override
    public void onResume(){
        if (packagesView != null) {
            packagesView.showTitleBar();
            packagesView.showProgress();
            map = packagesView.getFilterParams();
        }
        RequestParams params = new RequestParams();
        packagesInteractor.getAutoPackagesByJobId(this,map,params);
    }

    @Override
    public void onItemClicked(int position){
        if (packagesView != null) {
            packagesView.showMessage(String.format("Position %d clicked", position + 1));
        }
        //packagesView.navigateFeaturesActivity(position);
    }

    @Override
    public void onDestroy(){
        packagesView = null;
    }

    @Override
    public void onFinished(List<Package> items) {
        if (packagesView != null) {
            packagesView.setItems(items);
            packagesView.hideProgress();
        }
    }

    @Override
    public void onFailure(BaseResultObject items) {
        if (packagesView != null) {
            packagesView.setItemsError(items);
            packagesView.hideProgress();
        }
    }
}
