package com.pan.info.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pan.info.R;
import com.pan.info.base.BaseFragment;
import com.pan.info.presenter.MainPresentImpl;
import com.pan.info.presenter.MainPresenter;
import com.pan.info.ui.adapter.ViewPagerAdapter;
import com.pan.info.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public class HealthKnowledgeHomeFragment extends BaseFragment<MainPresenter> implements MainView {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinator;

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresentImpl(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.fragment_health_knowledge_home;
    }

    @Override
    protected void init(View view) {
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPagerAdapter.refresh(initData());
    }

    @Override
    public void showFragment(@IdRes int containerViewId, Fragment fragment) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public static HealthKnowledgeHomeFragment newInstance() {
        return new HealthKnowledgeHomeFragment();
    }

    private List<TestFragment> initData() {
        List<TestFragment> list = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            list.add(TestFragment.newInstance());
        }

        return list;
    }
}
