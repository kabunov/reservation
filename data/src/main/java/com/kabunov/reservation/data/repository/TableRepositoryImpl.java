package com.kabunov.reservation.data.repository;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.TableDataSource;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.datasource.local.LocalTableDataSource;
import com.kabunov.reservation.data.datasource.remote.RemoteTableDataSource;
import com.kabunov.reservation.domain.entity.Table;
import com.kabunov.reservation.domain.repository.TableRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TableRepositoryImpl implements TableRepository {

    private TableDataSource mRemoteDataSource;
    private TableDataSource mLocalDataSource;
    private DataConverter mDataConverter;

    @Inject
    public TableRepositoryImpl(@NonNull RemoteTableDataSource remoteDataSource, @NonNull LocalTableDataSource localDataSource, @NonNull DataConverter dataConverter) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mDataConverter = dataConverter;
    }

    @Override
    public Observable<List<Table>> getTables() {
        return mLocalDataSource.getTables()
                .onErrorResumeNext(mRemoteDataSource.getTables())
                .map(tableReservations -> mDataConverter.convertTables(tableReservations));
    }

    @Override
    public Observable<Void> reserve(int consumerId, int tableId) {
        return mLocalDataSource.reserve(consumerId, tableId);
    }

    @Override
    public Observable<Void> clearReservations() {
        return mLocalDataSource.clearReservations();
    }
}
