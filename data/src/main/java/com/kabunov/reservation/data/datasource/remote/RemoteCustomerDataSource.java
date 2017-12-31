package com.kabunov.reservation.data.datasource.remote;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.CustomerDataSource;
import com.kabunov.reservation.data.datasource.entity.CustomerData;
import com.kabunov.reservation.data.datasource.remote.api.RestApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteCustomerDataSource implements CustomerDataSource {

    private RestApi mRestApi;

    @Inject
    public RemoteCustomerDataSource(@NonNull RestApi restApi) {
        mRestApi = restApi;
    }

    @Override
    public Observable<List<CustomerData>> getCustomers() {
        return mRestApi.getCustomers();
    }

    @Override
    public Observable<List<CustomerData>> searchCustomers(String searchQuery) {
        throw new UnsupportedOperationException("Remote operation is not available");
    }
}
