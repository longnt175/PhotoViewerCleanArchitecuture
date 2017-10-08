package com.photoviewer.domain.usecases;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class SimpleSubscriber<T> implements Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }
}
