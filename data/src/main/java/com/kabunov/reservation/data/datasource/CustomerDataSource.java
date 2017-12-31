package com.kabunov.reservation.data.datasource;

import com.kabunov.reservation.data.datasource.entity.CustomerData;

import java.util.List;

import io.reactivex.Observable;

public interface CustomerDataSource {

    Observable<List<CustomerData>> getCustomers();

    Observable<List<CustomerData>> searchCustomers(String searchQuery);
}
