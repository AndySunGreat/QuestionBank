package com.aladdin.apps.questionbank.login.signup;

import com.aladdin.apps.questionbank.base.BaseModuleView;
import com.aladdin.apps.questionbank.base.BaseResultObject;

/**
 * Created by AndySun on 2016/10/8.
 */
public interface RegisterView extends BaseModuleView {
    void setItemsError(BaseResultObject bro);
    void navigatePackageActivity();
}
