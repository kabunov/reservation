package com.kabunov.reservation.data.datasource.local;

import com.kabunov.reservation.data.datasource.entity.CustomerData;

import java.util.List;

public interface LocalStorage {

    List<Boolean> getTables();

    void saveTables(List<Boolean> tables);

    List<CustomerData> getCustomers();

    void saveCustomers(List<CustomerData> customers);
}
