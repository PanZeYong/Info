package com.pan.info.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pan.info.R;
import com.pan.info.base.BaseFragment;
import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.presenter.HealthKnowledgePresenter;
import com.pan.info.presenter.HealthKnowledgePresenterImpl;
import com.pan.info.ui.adapter.HealthKnowledgeListAdapter;
import com.pan.info.ui.view.HealthKnowledgeView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Author : Pan
 * Date : 09/03/2017
 */

public class HealthKnowledgeListFragment extends BaseFragment<HealthKnowledgePresenter>
        implements HealthKnowledgeView {

    @BindView(R.id.recycler_view)
    XRecyclerView mRecyclerView;

    private HealthKnowledgeListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int initContentView() {
        return R.layout.fragment_health_knowledge_list;
    }

    @Override
    protected void init(View view) {
        mPresenter.attachView(this);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new HealthKnowledgeListAdapter(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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

    }

    @Override
    protected void unregisterRxBus() {

    }

    @Override
    public void getCategories(List<HealthKnowledgeCategoryBean.TngouBean> list) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    public static HealthKnowledgeListFragment newInstance() {
        return new HealthKnowledgeListFragment();
    }
}
