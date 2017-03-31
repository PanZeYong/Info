package com.pan.info.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pan.info.Constant;
import com.pan.info.api.ApiFactory;
import com.pan.info.base.RxBus;
import com.pan.info.base.RxEvent;
import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.bean.HealthKnowledgeListBean;
import com.pan.info.ui.view.HealthKnowledgeView;
import com.pan.info.util.NetworkUtils;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 13/03/2017
 */

public class HealthKnowledgePresenterImpl extends HealthKnowledgePresenter{

    private HealthKnowledgeView mView;

    @Override
    public void attachView(@NonNull HealthKnowledgeView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void getCategories(Context context) {
        ApiFactory.createHealthKnowledgeApi(context)
                .getCategories()
                .subscribeOn(Schedulers.io())
                .map(new Func1<HealthKnowledgeCategoryBean, List<HealthKnowledgeCategoryBean.TngouBean>>() {
                    @Override
                    public List<HealthKnowledgeCategoryBean.TngouBean> call(HealthKnowledgeCategoryBean bean) {
                        return bean.getTngou();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HealthKnowledgeCategoryBean.TngouBean>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d(Constant.TAG + "onCompleted");
                        RxBus.getInstance().post(new RxEvent.RxEventBuilder(Constant.CATEGORIES, true).build());
                    }

                    @Override
                    public void onError(Throwable e) {
                        RxBus.getInstance().post(new RxEvent.RxEventBuilder(Constant.CATEGORIES, false).build());
                        mView.showErrorMessage(e.getMessage());
                        Timber.d(Constant.TAG + "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<HealthKnowledgeCategoryBean.TngouBean> categories) {
//                        Timber.d("Size : " + categories.size());
                        mView.getCategories(categories);
                        Timber.d(Constant.TAG + "onNext");
                    }
                });
    }
}
