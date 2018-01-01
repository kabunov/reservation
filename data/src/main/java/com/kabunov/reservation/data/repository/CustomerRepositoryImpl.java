package com.kabunov.reservation.data.repository;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.CustomerDataSource;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.datasource.local.LocalCustomerDataSource;
import com.kabunov.reservation.data.datasource.remote.RemoteCustomerDataSource;
import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.repository.CustomerRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CustomerRepositoryImpl implements CustomerRepository {

    private CustomerDataSource mRemoteDataSource;
    private CustomerDataSource mLocalDataSource;
    private DataConverter mDataConverter;

    @Inject
    public CustomerRepositoryImpl(@NonNull RemoteCustomerDataSource remoteDataSource, @NonNull LocalCustomerDataSource localDataSource, @NonNull DataConverter dataConverter) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mDataConverter = dataConverter;
    }

    @Override
    public Observable<List<Customer>> getCustomers() {
        return mLocalDataSource.getCustomers()
                .onErrorResumeNext(mRemoteDataSource.getCustomers())
                .map(customers -> mDataConverter.convertCustomers(customers));
    }

    @Override
    public Observable<List<Customer>> searchCustomers(String searchQuery) {
        return mLocalDataSource.searchCustomers(searchQuery)
                .map(customers -> mDataConverter.convertCustomers(customers));
    }
}
