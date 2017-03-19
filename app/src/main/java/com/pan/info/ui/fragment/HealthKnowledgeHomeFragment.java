package com.pan.info.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pan.info.Constant;
import com.pan.info.R;
import com.pan.info.base.BaseFragment;
import com.pan.info.base.RxBus;
import com.pan.info.base.RxEvent;
import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.presenter.HealthKnowledgePresenter;
import com.pan.info.presenter.HealthKnowledgePresenterImpl;
import com.pan.info.ui.adapter.ViewPagerAdapter;
import com.pan.info.ui.view.HealthKnowledgeView;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public class HealthKnowledgeHomeFragment extends BaseFragment<HealthKnowledgePresenter>
        implements HealthKnowledgeView {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
//    @BindView(R.id.fab)
//    FloatingActionButton mFab;
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinator;

    private ViewPagerAdapter mViewPagerAdapter;

    private List<HealthKnowledgeCategoryBean.TngouBean> mCategories;

    @Override
    protected int initContentView() {
        return R.layout.fragment_health_knowledge_home;
    }

    @Override
    protected void init(View view) {
        mPresenter.attachView(this);
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mPresenter.getCategories(mContext);
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    @Override
    protected HealthKnowledgePresenter bindPresenter() {
        return new HealthKnowledgePresenterImpl();
    }

    @Override
    protected void registerRxBus() {
        RxBus.getInstance()
                .toObserverable(RxEvent.class)
                .compose(this.<RxEvent>bindToLifecycle())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        if (Constant.CATEGORIES == rxEvent.getType()) {
                            if (rxEvent.isSuccess()) {
                                mViewPagerAdapter.refresh(initData(), mCategories);
                            }
                        }
                    }
                });

    }

    @Override
    protected void unregisterRxBus() {

    }

    @Override
    public void getCategories(List<HealthKnowledgeCategoryBean.TngouBean> categories) {
        this.mCategories = categories;
    }

    @Override
    public void showErrorMessage(String message) {
//        Snackbar.make(mFab, message, Snackbar.LENGTH_SHORT).show();
    }

    public static HealthKnowledgeHomeFragment newInstance() {
        return new HealthKnowledgeHomeFragment();
    }

    private List<HealthKnowledgeListFragment> initData() {
        List<HealthKnowledgeListFragment> list = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            list.add(HealthKnowledgeListFragment.newInstance());
        }

        return list;
    }
}
