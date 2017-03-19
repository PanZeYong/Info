package com.pan.info.base;

import android.support.annotation.NonNull;
/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BasePresenter <V extends BaseView>{

    public abstract void attachView(@NonNull V view);

    public abstract void detachView();
}
