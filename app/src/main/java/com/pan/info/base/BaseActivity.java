package com.pan.info.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.pan.info.R;
import com.pan.info.util.RxLifecycle;
import com.pan.info.util.StatusBarUtils;

import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements RxLifecycle.Impl {

    private RxLifecycle mRxLifecycle = new RxLifecycle();

    protected P mPresenter;

    protected Context mContext;

    private FragmentTransaction mTransaction;

    /**
     * 返回要加载布局id
     *
     *
     * @return 返回布局id
     */
    protected abstract int initContentView();

    /**
     * 进行初始化工作
     */
    protected abstract void init();

    /**
     * 注册监听器
     */
    protected abstract void registerListener();

    /**
     * 释放资源
     */
    protected abstract void release();

    /**
     * 绑定Presenter
     */
    protected abstract P bindPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        this.mContext = this;
        this.mPresenter = checkNotNull(bindPresenter());

        ButterKnife.bind(this);
        setStatusBarColor();
        init();
        registerListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        mRxLifecycle.onDestroy();
        release();
    }

    @Override
    public RxLifecycle bindLifeCycle() {
        return mRxLifecycle;
    }

    protected void replace(@IdRes int containerViewId, Fragment fragment) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(containerViewId, fragment);
        mTransaction.commit();
    }

    protected void hide(Fragment fragment) {
        if (null != fragment) {
            mTransaction.hide(fragment);
        }
    }

    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtils.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer),
                    getColor(R.color.blue), 0);
        } else {
            StatusBarUtils.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer),
                    getResources().getColor(R.color.blue), 0);
        }
    }
}
