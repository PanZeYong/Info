package com.pan.info.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pan.info.util.RxLifecycle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BaseFragment<P extends BasePresenter>
        extends Fragment implements RxLifecycle.Impl{

    private RxLifecycle mRxLifecycle = new RxLifecycle();

    private P mPresenter;

    abstract protected P bindPresenter();

    private Unbinder unbind;

    /**
     * 返回要加载布局id
     *
     *
     * @return 返回布局id
     */
    protected abstract int initContentView();

    /**
     * 进行初始化工作，类似适配器
     *
     *
     * @param view 当前Fragment根View
     */
    protected abstract void init(View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initContentView(), container, false);
        this.unbind = ButterKnife.bind(this, view);
        this.mPresenter = checkNotNull(bindPresenter());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxLifecycle.onDestroy();
    }

    @Override
    public RxLifecycle bindLifeCycle() {
        return mRxLifecycle;
    }
}
