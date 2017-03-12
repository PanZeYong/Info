package com.pan.info.base;

import android.support.annotation.NonNull;

import com.pan.info.util.RxLifecycle;

import rx.Observable;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BasePresenter <V extends BaseView>{

    protected V mView;

    protected BasePresenter(@NonNull V view) {
        this.mView = checkNotNull(view);
    }

    protected <T>Observable.Transformer<T, T> bindLifecycle() {
        return RxLifecycle.bindLifecycle(mView);
    }
}
