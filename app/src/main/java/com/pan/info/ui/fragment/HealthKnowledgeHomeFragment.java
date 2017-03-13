package com.pan.info.ui.fragment;

import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pan.info.R;
import com.pan.info.base.BaseFragment;
import com.pan.info.presenter.HealthKnowledgePresenter;
import com.pan.info.presenter.HealthKnowledgePresenterImpl;
import com.pan.info.presenter.MainPresentImpl;
import com.pan.info.presenter.MainPresenter;
import com.pan.info.ui.adapter.ViewPagerAdapter;
import com.pan.info.ui.view.HealthKnowledgeView;
import com.pan.info.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinator;

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected HealthKnowledgePresenter bindPresenter() {
        return new HealthKnowledgePresenterImpl(this);
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

    public static HealthKnowledgeHomeFragment newInstance() {
        return new HealthKnowledgeHomeFragment();
    }

    private List<HealthKnowledgeListFragment> initData() {
        List<HealthKnowledgeListFragment> list = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            list.add(HealthKnowledgeListFragment.newInstance());
        }

        return list;
    }
}
