package com.kabunov.reservation.data.repository;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.CustomerDataSource;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.utils.ConnectivityUtil;
import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.repository.CustomerRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CustomerRepositoryImpl implements CustomerRepository {

    private ConnectivityUtil mConnectivityUtil;
    private CustomerDataSource mRemoteDataSource;
    private CustomerDataSource mLocalDataSource;
    private DataConverter mDataConverter;

    @Inject
    public CustomerRepositoryImpl(@NonNull ConnectivityUtil connectivityUtil, @NonNull CustomerDataSource remoteDataSource, @NonNull CustomerDataSource localDataSource, @NonNull DataConverter dataConverter) {
        mConnectivityUtil = connectivityUtil;
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mDataConverter = dataConverter;
    }

    @Override
    public Observable<List<Customer>> getCustomers() {
        return (mConnectivityUtil.isConnected() ? mRemoteDataSource : mLocalDataSource)
                .getCustomers()
                .map(customers -> mDataConverter.convertCustomers(customers));
    }

    @Override
    public Observable<List<Customer>> searchCustomers(String searchQuery) {
        return null;
    }
}
