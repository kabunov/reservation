package com.kabunov.reservation.domain.interactor;

import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;
import com.kabunov.reservation.domain.interactor.base.Interactor;
import com.kabunov.reservation.domain.repository.TableRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ReserveTableInteractor extends Interactor<Void, ReserveTableInteractor.Params> {

    private TableRepository mTableRepository;

    @Inject
    public ReserveTableInteractor(ThreadExecutor backgroundThreadExecutor,
                                  PostExecutionThread postExecutionThread,
                                  TableRepository tableRepository) {
        super(backgroundThreadExecutor, postExecutionThread);
        mTableRepository = tableRepository;
    }

    @Override
    public Observable<Void> buildObservable(ReserveTableInteractor.Params params) {
        return mTableRepository.reserve(params.mConsumerId, params.mTableId);
    }

    public static final class Params {
        private int mConsumerId;
        private int mTableId;

        public Params(int consumerId, int tableId) {
            mConsumerId = consumerId;
            mTableId = tableId;
        }
    }
}
