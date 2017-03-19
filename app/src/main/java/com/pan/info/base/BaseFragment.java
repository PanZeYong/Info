package com.pan.info.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pan.info.InfoApplication;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Author : Pan
 * Date : 1/17/17
 */

public abstract class BaseFragment<P extends BasePresenter>
        extends RxFragment {

    protected P mPresenter;

    private Unbinder unbind;

    protected Context mContext;

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

    protected abstract void release();

    abstract protected P bindPresenter();

    abstract protected void registerRxBus();

    abstract protected void unregisterRxBus();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initContentView(), container, false);
        this.unbind = ButterKnife.bind(this, view);
        this.mPresenter = checkNotNull(bindPresenter());
        this.mContext = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        registerRxBus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
        unregisterRxBus();
        release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        InfoApplication.getRefWatcher(getActivity()).watch(this);
    }
}
