package com.photoviewer.domain.usecases;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public abstract class UseCase<T> {

    protected Disposable disposable = Disposables.empty();
    private final Scheduler executionScheduler;
    private final Scheduler observingScheduler;

    protected UseCase(Scheduler executionScheduler,
                      Scheduler observingScheduler) {
        this.executionScheduler = executionScheduler;
        this.observingScheduler = observingScheduler;
    }

    protected abstract Observable<T> call();

    public <S extends Observer<T> & Disposable> void execute(S useCaseDisposable) {
        this.disposable = this.call()
                .subscribeOn(executionScheduler)
                .observeOn(observingScheduler)
                .subscribeWith(useCaseDisposable);
    }

    public void unsubscribe() {
        if (!this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    public boolean isUnsubscribed() {
        return this.disposable.isDisposed();
    }
}