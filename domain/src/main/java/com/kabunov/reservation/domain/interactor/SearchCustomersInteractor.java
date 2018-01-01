package com.kabunov.reservation.domain.interactor;

import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;
import com.kabunov.reservation.domain.interactor.base.Interactor;
import com.kabunov.reservation.domain.repository.CustomerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class SearchCustomersInteractor extends Interactor<List<Customer>, SearchCustomersInteractor.Params> {

    private CustomerRepository mCustomerRepository;

    @Inject
    public SearchCustomersInteractor(ThreadExecutor backgroundThreadExecutor,
                                     PostExecutionThread postExecutionThread,
                                     CustomerRepository customerRepository) {
        super(backgroundThreadExecutor, postExecutionThread);
        mCustomerRepository = customerRepository;
    }

    @Override
    public Observable<List<Customer>> buildObservable(SearchCustomersInteractor.Params params) {
        return mCustomerRepository.searchCustomers(params.mSearchQuery);
    }

    public static final class Params {
        private String mSearchQuery;

        public Params(String searchQuery) {
            mSearchQuery = searchQuery;
        }
    }
}
