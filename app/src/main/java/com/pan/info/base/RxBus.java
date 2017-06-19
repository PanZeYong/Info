package com.pan.info.base;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Author : Pan
 * Date : 1/24/17
 */

public class RxBus {
    private static volatile RxBus sRxBus;

    private static final Subject<Object, Object> _bus =
            new SerializedSubject<>(PublishSubject.create());

    private RxBus() {}

    public static RxBus getInstance() {
        if (null == sRxBus) {
            synchronized (RxBus.class) {
                if (null == sRxBus) {
                    sRxBus = new RxBus();
                }
            }
        }

        return sRxBus;
    }

    public void post(Object... objects) {
        if (null == objects) return;
        for (Object object : objects) {
            getInstance()._bus.onNext(object);
        }
    }

    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return getInstance()._bus.ofType(eventType);
    }
}
