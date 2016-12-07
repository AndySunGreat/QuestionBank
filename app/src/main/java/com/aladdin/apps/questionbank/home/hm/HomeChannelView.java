package com.aladdin.apps.questionbank.home.hm;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySun on 2016/12/5.
 */
public interface HomeChannelView {
    void setOrdersItems(List<Order> mData);
    void setOrdersItemsError(BaseResultObject bro);
    void setPackageItems(List<Package> packageItems);
    void setPackageItemsError(BaseResultObject bro);

    Map getFilterMap();
    void setFilterMap(Map filterMap);
    void nagivateQuestionAcitivity();
}
