package com.pan.info.util;

import android.support.annotation.NonNull;

import com.pan.info.base.BaseView;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Author : Pan
 * Date : 1/24/17
 */

public class RxLifecycle {
    public enum Event {
        DESTROY
    }

    private final BehaviorSubject<Event> behavior = BehaviorSubject.create();

    public void onDestroy() {
        behavior.onNext(Event.DESTROY);
    }

    public static <T> Observable.Transformer<T, T> bindLifecycle(@NonNull BaseView view) {
        return new CheckLifeCycleTransformer<>(view.bindLifeCycle().behavior);
    }

    public interface Impl {
        RxLifecycle bindLifeCycle();
    }

    public static class CheckLifeCycleTransformer<T> implements Observable.Transformer<T, T> {

        private BehaviorSubject<Event> mBehavior;

        public CheckLifeCycleTransformer(BehaviorSubject<Event> behavior) {
            mBehavior = behavior;
        }

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.takeUntil(mBehavior.skipWhile(new Func1<Event, Boolean>() {
                @Override
                public Boolean call(Event event) {
                    return event != Event.DESTROY.DESTROY;
                }
            }));
        }
    }
}
