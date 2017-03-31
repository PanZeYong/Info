package com.pan.info.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pan.info.util.NetworkUtils;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BasePresenter <V extends BaseView>{

    public abstract void attachView(@NonNull V view);

    public abstract void detachView();

    public boolean checkIsNetwrok(Context context) {
        if (NetworkUtils.isNetworkConnected(context)) {
            return true;
        } else {
            return false;
        }
    }
}
