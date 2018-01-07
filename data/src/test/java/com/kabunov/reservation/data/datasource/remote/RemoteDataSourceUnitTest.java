package com.kabunov.reservation.data.datasource.remote;

import com.kabunov.reservation.data.datasource.local.LocalStorage;
import com.kabunov.reservation.data.datasource.remote.api.RestApi;

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
public class RemoteDataSourceUnitTest {

    private RemoteCustomerDataSource mRemoteCustomerDataSource;
    private RemoteTableDataSource mRemoteTableDataSource;

    @Mock
    private RestApi mMockRestApi;

    @Mock
    private LocalStorage mMockLocalStorage;

    @Before
    public void init() {
        mRemoteCustomerDataSource = new RemoteCustomerDataSource(mMockRestApi, mMockLocalStorage);
        mRemoteTableDataSource = new RemoteTableDataSource(mMockRestApi, mMockLocalStorage);

        given(mMockRestApi.getCustomers()).willReturn(Observable.just(new ArrayList<>()));
        given(mMockRestApi.getTables()).willReturn(Observable.just(new ArrayList<>()));
    }

    @Test
    public void testGetCustomers() throws Exception {
        mRemoteCustomerDataSource.getCustomers();
        verify(mMockRestApi).getCustomers();
    }

    @Test
    public void testGetTables() throws Exception {
        mRemoteTableDataSource.getTables();
        verify(mMockRestApi).getTables();
    }
}