package com.kabunov.reservation.data;

import com.kabunov.reservation.data.datasource.remote.RemoteCustomerDataSource;
import com.kabunov.reservation.data.datasource.remote.RemoteTableDataSource;
import com.kabunov.reservation.data.datasource.remote.api.RestApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoteDataSourceUnitTest {

    private RemoteCustomerDataSource mRemoteCustomerDataSource;
    private RemoteTableDataSource mRemoteTableDataSource;

    @Mock
    private RestApi mMockRestApi;

    @Before
    public void init() {
        mRemoteCustomerDataSource = new RemoteCustomerDataSource(mMockRestApi);
        mRemoteTableDataSource = new RemoteTableDataSource(mMockRestApi);
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