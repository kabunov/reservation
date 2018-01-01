package com.kabunov.reservation.data.datasource.entity;

import android.support.annotation.NonNull;

import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.entity.Table;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * To convert Data layer entities to Domain layer entities
 */
public class DataConverter {

    @Inject
    public DataConverter() {
    }

    @NonNull
    public List<Customer> convertCustomers(@NonNull List<CustomerData> customers) {
        List<Customer> res = new ArrayList<>(customers.size());
        for (CustomerData customerData : customers) {
            if (customerData == null) {
                continue;
            }
            Customer customer = new Customer();
            customer.setId(customerData.getId());
            customer.setFirstName(customerData.getFirstName());
            customer.setLastName(customerData.getLastName());
            res.add(customer);
        }
        return res;
    }

    @NonNull
    public List<Table> convertTables(@NonNull List<Boolean> tableReservations) {
        List<Table> res = new ArrayList<>(tableReservations.size());
        for (int i = 0; i < tableReservations.size(); i++) {
            Boolean available = tableReservations.get(i);
            if (available == null) {
                continue;
            }
            Table table = new Table();
            table.setId(i);
            table.setReserved(!available);
            res.add(table);
        }
        return res;
    }
}
