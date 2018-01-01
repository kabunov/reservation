package com.kabunov.reservation.data;

import com.kabunov.reservation.data.datasource.CustomerDataSource;
import com.kabunov.reservation.data.datasource.TableDataSource;
import com.kabunov.reservation.data.datasource.entity.DataConverter;
import com.kabunov.reservation.data.repository.CustomerRepositoryImpl;
import com.kabunov.reservation.data.repository.TableRepositoryImpl;
import com.kabunov.reservation.data.utils.ConnectivityUtil;

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
    private ConnectivityUtil mConnectivityUtil;
    @Mock
    private CustomerDataSource mRemoteCustomerDataSource;
    @Mock
    private CustomerDataSource mLocalCustomerDataSource;
    @Mock
    private TableDataSource mRemoteTableDataSource;
    @Mock
    private TableDataSource mLocalTableDataSource;
    @Mock
    private DataConverter mDataConverter;

    @Before
    public void init() {
        mCustomerRepository = new CustomerRepositoryImpl(mConnectivityUtil, mRemoteCustomerDataSource, mLocalCustomerDataSource, mDataConverter);
        mTableRepository = new TableRepositoryImpl(mConnectivityUtil, mRemoteTableDataSource, mLocalTableDataSource, mDataConverter);

        given(mRemoteCustomerDataSource.getCustomers()).willReturn(Observable.just(new ArrayList<>()));
        given(mLocalCustomerDataSource.getCustomers()).willReturn(Observable.just(new ArrayList<>()));
        given(mRemoteTableDataSource.getTables()).willReturn(Observable.just(new ArrayList<>()));
        given(mLocalTableDataSource.getTables()).willReturn(Observable.just(new ArrayList<>()));
    }

    @Test
    public void testGetRemoteCustomers() throws Exception {
        given(mConnectivityUtil.isConnected()).willReturn(true);

        mCustomerRepository.getCustomers();
        verify(mRemoteCustomerDataSource).getCustomers();
    }

    @Test
    public void testGetLocalCustomers() throws Exception {
        given(mConnectivityUtil.isConnected()).willReturn(false);

        mCustomerRepository.getCustomers();
        verify(mLocalCustomerDataSource).getCustomers();
    }

    @Test
    public void testGetRemoteTables() throws Exception {

        given(mConnectivityUtil.isConnected()).willReturn(true);

        mTableRepository.getTables();
        verify(mRemoteTableDataSource).getTables();
    }

    @Test
    public void testGetLocalTables() throws Exception {

        given(mConnectivityUtil.isConnected()).willReturn(false);

        mTableRepository.getTables();
        verify(mLocalTableDataSource).getTables();
    }
}