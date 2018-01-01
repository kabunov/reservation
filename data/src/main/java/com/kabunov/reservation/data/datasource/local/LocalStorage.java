package com.kabunov.reservation.data.datasource.local;

import com.kabunov.reservation.data.datasource.entity.CustomerData;

import java.util.List;

/**
 * App should work offline, so we use local storage to save the data
 */
public interface LocalStorage {

    List<Boolean> getTables();

    void saveTables(List<Boolean> tables);

    List<CustomerData> getCustomers();

    void saveCustomers(List<CustomerData> customers);
}
