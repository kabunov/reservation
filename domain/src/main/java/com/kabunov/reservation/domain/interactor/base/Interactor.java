package com.kabunov.reservation.domain.interactor.base;


import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class Interactor<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    protected Interactor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link Interactor}.
     */
    public abstract Observable<T> buildObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildObservable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.buildObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
