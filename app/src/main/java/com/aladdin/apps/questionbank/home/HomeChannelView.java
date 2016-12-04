package com.aladdin.apps.questionbank.home;

import com.aladdin.apps.questionbank.base.BaseResultObject;
import com.aladdin.apps.questionbank.data.bean.Order;
import com.aladdin.apps.questionbank.data.bean.Package;

import java.util.List;

/**
 * Created by AndySun on 2016/12/5.
 */
public interface HomeChannelView {
    void setItems(List<Order> mData);
    void setItemsError(BaseResultObject bro);
}
