package com.kabunov.reservation.domain.interactor;

import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;
import com.kabunov.reservation.domain.interactor.base.Interactor;
import com.kabunov.reservation.domain.repository.TableRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ClearReservationsInteractor extends Interactor<Void, ClearReservationsInteractor.Params> {

    private TableRepository mTableRepository;

    @Inject
    public ClearReservationsInteractor(ThreadExecutor backgroundThreadExecutor,
                                       PostExecutionThread postExecutionThread,
                                       TableRepository tableRepository) {
        super(backgroundThreadExecutor, postExecutionThread);
        mTableRepository = tableRepository;
    }

    @Override
    public Observable<Void> buildObservable(ClearReservationsInteractor.Params params) {
        return mTableRepository.clearReservations();
    }

    public static final class Params {
    }
}
