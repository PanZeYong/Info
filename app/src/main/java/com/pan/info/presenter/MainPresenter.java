package com.pan.info.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.pan.info.ui.view.MainView;
import com.pan.info.base.BasePresenter;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public abstract class MainPresenter extends BasePresenter<MainView> {
    /**
     * NavigationView MenuItem点击事件
     * @param item item
     */
    public abstract void clickMenuItem(Context context, MenuItem item);
}
