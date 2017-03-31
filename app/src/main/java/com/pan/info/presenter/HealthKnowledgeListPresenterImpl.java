package com.pan.info.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pan.info.Constant;
import com.pan.info.api.ApiFactory;
import com.pan.info.base.RxBus;
import com.pan.info.base.RxEvent;
import com.pan.info.bean.HealthKnowledgeListBean;
import com.pan.info.ui.view.HealthKnowledgeListView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 25/03/2017
 */

public class HealthKnowledgeListPresenterImpl extends HealthKnowledgeListPresenter {

    private HealthKnowledgeListView mView;

    @Override
    public void attachView(@NonNull HealthKnowledgeListView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void getList(Context context, int categoryId, int page, int rows) {
        ApiFactory.createHealthKnowledgeApi(context)
                .getList(categoryId, page, rows)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showProgressBar();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HealthKnowledgeListBean, List<HealthKnowledgeListBean.TngouBean>>() {
                    @Override
                    public List<HealthKnowledgeListBean.TngouBean> call(HealthKnowledgeListBean bean) {
                        return bean.getTngou();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HealthKnowledgeListBean.TngouBean>>() {
                    @Override
                    public void onCompleted() {
                        if (null != mView) {
                            mView.hideProgressBar();
                            RxBus.getInstance().post(new RxEvent.RxEventBuilder(Constant.LIST, true).build());
                        }

                        Timber.d(Constant.TAG + "omCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != mView) {
                            mView.hideProgressBar();
                            mView.showErrorMessage(e.getMessage());
                            RxBus.getInstance().post(new RxEvent.RxEventBuilder(Constant.LIST, false).build());
                        }

                        Timber.d(Constant.TAG + "onError " + e.getStackTrace()[0]);
                    }

                    @Override
                    public void onNext(List<HealthKnowledgeListBean.TngouBean> list) {
                        if (null != mView) {
                            mView.getList(list);
                        }
                        Timber.d(Constant.TAG + "onNext");
                    }
                });
    }
}
