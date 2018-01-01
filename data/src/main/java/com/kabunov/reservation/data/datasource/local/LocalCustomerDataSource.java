package com.kabunov.reservation.data.datasource.local;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.CustomerDataSource;
import com.kabunov.reservation.data.datasource.entity.CustomerData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalCustomerDataSource implements CustomerDataSource {

    private LocalStorage mLocalStorage;

    @Inject
    public LocalCustomerDataSource(@NonNull LocalStorage localStorage) {
        mLocalStorage = localStorage;
    }

    @Override
    public Observable<List<CustomerData>> getCustomers() {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(mLocalStorage.getCustomers());
            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<List<CustomerData>> searchCustomers(String searchQuery) {
        return Observable.create(subscriber -> {
            try {
                List<CustomerData> customers = mLocalStorage.getCustomers();
                List<CustomerData> res = new ArrayList<>();
                String query = searchQuery.toLowerCase();
                for (CustomerData customer : customers) {
                    if ((customer.getFirstName() != null && customer.getFirstName().toLowerCase().contains(query))
                            || (customer.getLastName() != null && customer.getLastName().toLowerCase().contains(query)))
                    {
                        res.add(customer);
                    }
                }
                subscriber.onNext(res);
            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                subscriber.onComplete();
            }
        });
    }
}
