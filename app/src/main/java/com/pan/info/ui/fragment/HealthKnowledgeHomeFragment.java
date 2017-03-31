package com.pan.info.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

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

    private Subscription mSubscription;

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
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
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
        mSubscription = RxBus.getInstance()
                .toObserverable(RxEvent.class)
                .compose(this.<RxEvent>bindToLifecycle())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        Timber.d("RxEvent");
                        if (Constant.CATEGORIES.equals(rxEvent.getType())) {
                            if (rxEvent.isSuccess()) {
                                mViewPagerAdapter.refresh(initFragment(), mCategories);
                            }
                        }
                    }
                });

    }

    @Override
    protected void unregisterRxBus() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    protected void lazyLoadData() {
    }

    @Override
    public void getCategories(List<HealthKnowledgeCategoryBean.TngouBean> categories) {
        this.mCategories = categories;
    }

    @Override
    public void showErrorMessage(String message) {
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume");
        if (mPresenter.checkIsNetwrok(mContext)) {
            mPresenter.getCategories(mContext);
        } else {
            Timber.d("Dialog");
//            new CommonDialog.Builder(mContext)
//                    .setLayoutId(R.layout.dialog)
//                    .setTitle(getString(R.string.dialog_title))
//                    .setMessage(getString(R.string.dialog_message))
//                    .setNegativeButton(getString(R.string.dialog_cancel))
//                    .setPositiveButton(getString(R.string.dialog_sure))
//                    .setOnDialogButtonClickListener(this)
//                    .create()
//                    .show();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    public static HealthKnowledgeHomeFragment newInstance() {
        return new HealthKnowledgeHomeFragment();
    }

    private List<HealthKnowledgeListFragment> initFragment() {
        List<HealthKnowledgeListFragment> list = new ArrayList<>();

        for (int i = 0; i < mCategories.size(); i++) {
            list.add(HealthKnowledgeListFragment.newInstance(mCategories.get(i).getId()));
        }

        return list;
    }
}
