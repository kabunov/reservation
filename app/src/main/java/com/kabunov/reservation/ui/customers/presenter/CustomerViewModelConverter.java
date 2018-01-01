package com.kabunov.reservation.ui.customers.presenter;

import android.text.TextUtils;

import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CustomerViewModelConverter {

    @Inject
    CustomerViewModelConverter() {
    }

    public CustomerViewModel convertCustomer(Customer customer) {

        //first letter of first or last name
        String firstLetter = "";
        if (!TextUtils.isEmpty(customer.getLastName())) {
            firstLetter = customer.getLastName().substring(0, 1);
        } else if (!TextUtils.isEmpty(customer.getFirstName())) {
            firstLetter = customer.getFirstName().substring(0, 1);
        }

        return new CustomerViewModel(
                customer.getId(),
                firstLetter,
                String.format("%s %s", customer.getFirstName(), customer.getLastName()).trim()
        );
    }

    public List<CustomerViewModel> convertCustomers(List<Customer> customers) {

        List<CustomerViewModel> res = new ArrayList<>();

        for (Customer customer : customers) {
            res.add(convertCustomer(customer));
        }

        return res;
    }
}
