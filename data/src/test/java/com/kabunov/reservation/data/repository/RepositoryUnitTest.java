package com.kabunov.reservation.data.repository;

import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.datasource.local.LocalCustomerDataSource;
import com.kabunov.reservation.data.datasource.local.LocalTableDataSource;
import com.kabunov.reservation.data.datasource.remote.RemoteCustomerDataSource;
import com.kabunov.reservation.data.datasource.remote.RemoteTableDataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryUnitTest {

    private CustomerRepositoryImpl mCustomerRepository;
    private TableRepositoryImpl mTableRepository;

    @Mock
    private RemoteCustomerDataSource mRemoteCustomerDataSource;
    @Mock
    private LocalCustomerDataSource mLocalCustomerDataSource;
    @Mock
    private RemoteTableDataSource mRemoteTableDataSource;
    @Mock
    private LocalTableDataSource mLocalTableDataSource;
    @Mock
    private DataConverter mDataConverter;

    @Before
    public void init() {
        mCustomerRepository = new CustomerRepositoryImpl(mRemoteCustomerDataSource, mLocalCustomerDataSource, mDataConverter);
        mTableRepository = new TableRepositoryImpl(mRemoteTableDataSource, mLocalTableDataSource, mDataConverter);

        given(mRemoteCustomerDataSource.getCustomers()).willReturn(Observable.just(new ArrayList<>()));
        given(mLocalCustomerDataSource.getCustomers()).willReturn(Observable.just(new ArrayList<>()));
        given(mRemoteTableDataSource.getTables()).willReturn(Observable.just(new ArrayList<>()));
        given(mLocalTableDataSource.getTables()).willReturn(Observable.just(new ArrayList<>()));
    }

    @Test
    public void testGetRemoteCustomers() throws Exception {
        mCustomerRepository.getCustomers();
        verify(mRemoteCustomerDataSource).getCustomers();
    }

    @Test
    public void testGetLocalCustomers() throws Exception {
        mCustomerRepository.getCustomers();
        verify(mLocalCustomerDataSource).getCustomers();
    }

    @Test
    public void testGetRemoteTables() throws Exception {
        mTableRepository.getTables();
        verify(mRemoteTableDataSource).getTables();
    }

    @Test
    public void testGetLocalTables() throws Exception {
        mTableRepository.getTables();
        verify(mLocalTableDataSource).getTables();
    }
}