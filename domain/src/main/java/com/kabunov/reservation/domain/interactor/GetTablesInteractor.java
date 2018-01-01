package com.kabunov.reservation.domain.interactor;

import com.kabunov.reservation.domain.entity.Table;
import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;
import com.kabunov.reservation.domain.interactor.base.Interactor;
import com.kabunov.reservation.domain.repository.TableRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetTablesInteractor extends Interactor<List<Table>, GetTablesInteractor.Params> {

    private TableRepository mTableRepository;

    @Inject
    public GetTablesInteractor(ThreadExecutor backgroundThreadExecutor,
                               PostExecutionThread postExecutionThread,
                               TableRepository tableRepository) {
        super(backgroundThreadExecutor, postExecutionThread);
        mTableRepository = tableRepository;
    }

    @Override
    public Observable<List<Table>> buildObservable(GetTablesInteractor.Params params) {
        return mTableRepository.getTables();
    }

    public static final class Params {
    }
}
