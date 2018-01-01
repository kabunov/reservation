package com.kabunov.reservation.domain.repository;

import com.kabunov.reservation.domain.entity.Customer;

import java.util.List;

import io.reactivex.Observable;

public interface CustomerRepository {

    Observable<List<Customer>> getCustomers();

    Observable<List<Customer>> searchCustomers(String searchQuery);
}
