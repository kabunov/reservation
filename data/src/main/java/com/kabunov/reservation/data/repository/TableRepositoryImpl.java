package com.kabunov.reservation.data.repository;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.TableDataSource;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.utils.ConnectivityUtil;
import com.kabunov.reservation.domain.entity.Table;
import com.kabunov.reservation.domain.repository.TableRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TableRepositoryImpl implements TableRepository {

    private  ConnectivityUtil mConnectivityUtil;
    private  TableDataSource mRemoteDataSource;
    private  TableDataSource mLocalDataSource;
    private DataConverter mDataConverter;

    @Inject
    public TableRepositoryImpl(@NonNull ConnectivityUtil connectivityUtil, @NonNull TableDataSource remoteDataSource, @NonNull TableDataSource localDataSource, @NonNull DataConverter dataConverter) {
        mConnectivityUtil = connectivityUtil;
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mDataConverter = dataConverter;
    }

    @Override
    public Observable<List<Table>> getTables() {
        return (mConnectivityUtil.isConnected() ? mRemoteDataSource : mLocalDataSource)
                .getTables()
                .map(tableReservations -> mDataConverter.convertTables(tableReservations));
    }

    @Override
    public Observable<Table> reserve(int tableId) {
        return null;
    }

    @Override
    public Observable<Void> clearReservations() {
        return null;
    }
}
