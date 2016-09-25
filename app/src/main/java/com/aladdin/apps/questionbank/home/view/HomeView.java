package com.aladdin.apps.questionbank.home.view;

/**
 * Created by AndySun on 2016/9/22.
 */
public interface HomeView {

    // 创建标题导航栏
    public void createTitleBar();

    // 创建页面切换样式的内容
    public void createViewPagerTab();
    // 创建底部导航栏
    public void createBottomBar();

    // 异步更新的BadgeView
    public void asyncBadgeView();
}
