package com.pan.info.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pan.info.Constant;
import com.pan.info.R;
import com.pan.info.base.BaseFragment;
import com.pan.info.base.RxBus;
import com.pan.info.base.RxEvent;
import com.pan.info.bean.HealthKnowledgeListBean;
import com.pan.info.presenter.HealthKnowledgeListPresenter;
import com.pan.info.presenter.HealthKnowledgeListPresenterImpl;
import com.pan.info.ui.adapter.HealthKnowledgeListAdapter;
import com.pan.info.ui.view.HealthKnowledgeListView;
import com.pan.info.ui.view.SpaceItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 09/03/2017
 */

public class HealthKnowledgeListFragment extends BaseFragment<HealthKnowledgeListPresenter>
        implements HealthKnowledgeListView {

    @BindView(R.id.recycler_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.pb_load)
    ProgressBar mLoadProgressBar;

    private final static int ROWS = 20;
    @BindView(R.id.tv_empty)
    TextView mEmpty;

    private HealthKnowledgeListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private List<HealthKnowledgeListBean.TngouBean> mList;

    private int mCategoryId;

    private int mCurrentPage = 1;

    private Subscription mSubscription;

    // 是否已经加载过数据
    private boolean isLoadData = false;

    @Override
    protected int initContentView() {
        return R.layout.fragment_health_knowledge_list;
    }

    @Override
    protected void init(View view) {
        mCategoryId = getArguments().getInt("categoryId");
        mPresenter.attachView(this);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new HealthKnowledgeListAdapter(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        mRecyclerView.addOnScrollListener(listener);
    }

    @Override
    protected void release() {
        mPresenter.detachView();
        if (null != listener && null != mRecyclerView) {
            mRecyclerView.removeOnScrollListener(listener);
        }
    }

    @Override
    protected HealthKnowledgeListPresenter bindPresenter() {
        return new HealthKnowledgeListPresenterImpl();
    }

    @Override
    protected void registerRxBus() {
        mSubscription = RxBus.getInstance()
                .toObserverable(RxEvent.class)
                .compose(this.<RxEvent>bindToLifecycle())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        Timber.d("Success : " + mCategoryId);

                        if (Constant.LIST.equals(rxEvent.getType())) {
                            if (rxEvent.isSuccess()) {
                                mAdapter.refresh(mList);
                            }
                        }
                    }
                });
    }

    @Override
    protected void unregisterRxBus() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription.isUnsubscribed();
        }
    }

    @Override
    protected void lazyLoadData() {
        if (!isLoadData) {
            if (mPresenter.checkIsNetwork(mContext)) {
                mPresenter.getList(mContext, mCategoryId, mCurrentPage, ROWS);
            }
        }

        Timber.d("lazyLoadData : " + mCategoryId + "-" + isLoadData);
    }

    @Override
    public void showErrorMessage(String message) {
        Timber.d(message);
    }

    @Override
    public void getList(List<HealthKnowledgeListBean.TngouBean> list) {
        this.mList = list;
    }

    @Override
    public void showProgressBar() {
        mLoadProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mLoadProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        mEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        mEmpty.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    private XRecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == XRecyclerView.SCROLL_STATE_IDLE) {
                Timber.d("SCROLL_STATE_IDLE");
                Picasso.with(mContext).resumeTag(mContext);
            } else {
                Timber.d("Pause");
                Picasso.with(mContext).pauseTag(mContext);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    public static HealthKnowledgeListFragment newInstance(int categoryId) {
        HealthKnowledgeListFragment fragment = new HealthKnowledgeListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
