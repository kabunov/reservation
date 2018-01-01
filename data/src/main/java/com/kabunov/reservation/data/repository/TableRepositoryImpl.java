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

/**
 * Some of the methods available for remote and local data, some - only local
 */
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

    /**
     * Tries to load local data, if it doesn't exist, calls remote data source and stores the result locally
     */
    @Override
    public Observable<List<Table>> getTables() {
        return mLocalDataSource.getTables()
                .onErrorResumeNext(mRemoteDataSource.getTables())
                .map(tableReservations -> mDataConverter.convertTables(tableReservations));
    }

    /**
     * Stores reservation data locally (since no remote api for this)
     */
    @Override
    public Observable<Void> reserve(int consumerId, int tableId) {
        return mLocalDataSource.reserve(consumerId, tableId);
    }

    /**
     * Clears all the reservation data locally (since no remote api for this)
     */
    @Override
    public Observable<Void> clearReservations() {
        return mLocalDataSource.clearReservations();
    }
}
