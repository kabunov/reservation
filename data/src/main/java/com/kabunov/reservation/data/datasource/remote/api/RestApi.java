package com.kabunov.reservation.data.datasource.remote.api;

import com.kabunov.reservation.data.datasource.entity.CustomerData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("table-map.json")
    Observable<List<Boolean>> getTables();

    @GET("customer-list.json")
    Observable<List<CustomerData>> getCustomers();
}