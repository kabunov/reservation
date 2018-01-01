package com.kabunov.reservation.data;

import com.kabunov.reservation.data.datasource.entity.CustomerData;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.domain.entity.Customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DataConverterUnitTest {

    private DataConverter mDataConverter;

    @Before
    public void init() {
        mDataConverter = new DataConverter();
    }

    @Test
    public void testConvertCustomers() throws Exception {
        List<CustomerData> customersData = new ArrayList<>();
        customersData.add(new CustomerData());
        customersData.add(new CustomerData());
        List<Customer> customers = mDataConverter.convertCustomers(customersData);

        assertNotNull(customers);
        assertThat(customers.size(), is(customersData.size()));
    }

    @Test
    public void testConvertCustomersWithNull() throws Exception {
        List<CustomerData> customersData = new ArrayList<>();
        customersData.add(new CustomerData());
        customersData.add(null);
        customersData.add(new CustomerData());
        List<Customer> customers = mDataConverter.convertCustomers(customersData);

        assertNotNull(customers);
        assertThat(customers.size(), is(customersData.size() - 1));
    }
}