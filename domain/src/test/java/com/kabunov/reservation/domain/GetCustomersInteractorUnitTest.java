package com.kabunov.reservation.domain;


import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;
import com.kabunov.reservation.domain.interactor.GetCustomersInteractor;
import com.kabunov.reservation.domain.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetCustomersInteractorUnitTest {

    private GetCustomersInteractor mGetCustomersInteractor;

    @Mock
    private ThreadExecutor mMockThreadExecutor;
    @Mock
    private PostExecutionThread mMockPostExecutionThread;
    @Mock
    private CustomerRepository mMockCustomerRepository;

    @Before
    public void setUp() {
        mGetCustomersInteractor = new GetCustomersInteractor(mMockThreadExecutor, mMockPostExecutionThread, mMockCustomerRepository);
    }

    @Test
    public void testGetCustomersInteractor() {
        mGetCustomersInteractor.buildObservable(null);

        verify(mMockCustomerRepository).getCustomers();
        verifyNoMoreInteractions(mMockCustomerRepository);
        verifyZeroInteractions(mMockThreadExecutor);
        verifyZeroInteractions(mMockPostExecutionThread);
    }
}
