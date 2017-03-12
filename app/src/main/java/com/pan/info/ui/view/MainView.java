package com.pan.info.ui.view;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.pan.info.base.BaseView;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public interface MainView extends BaseView {
    /**
     * 点击NavigationView 菜单项展示对应Fragment
     *
     * @param fragment 要展示Fragment
     * @param containerViewId Fragment资源ID
     */
    void showFragment(@IdRes int containerViewId, Fragment fragment);

    /**
     * Toolbar显示每次跳转标题
     */
    void setTitle(String title);
}
