package com.kabunov.reservation.ui.customers.presenter;

import android.text.TextUtils;

import com.kabunov.reservation.R;
import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.interactor.GetCustomersInteractor;
import com.kabunov.reservation.domain.interactor.SearchCustomersInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;
import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;
import com.kabunov.reservation.ui.customers.view.CustomersView;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.BehaviorSubject;

public class CustomersPresenter extends BasePresenter<CustomersView> {

    private GetCustomersInteractor mGetCustomersInteractor;
    private SearchCustomersInteractor mSearchCustomersInteractor;
    private CustomerViewModelConverter mViewModelConverter;

    private List<CustomerViewModel> mAllData;

    private BehaviorSubject<String> mSearchDebouncer = BehaviorSubject.create();

    @Inject
    CustomersPresenter(GetCustomersInteractor getCustomersInteractor,
                       SearchCustomersInteractor searchCustomersInteractor,
                       CustomerViewModelConverter viewModelConverter) {
        mGetCustomersInteractor = getCustomersInteractor;
        mSearchCustomersInteractor = searchCustomersInteractor;
        mViewModelConverter = viewModelConverter;

        mSearchDebouncer
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doSearch);

        mAllData = new ArrayList<>();
    }

    public void onViewReady() {
        loadData();
    }

    private void loadData() {
        mView.showLoading();
        mGetCustomersInteractor.execute(new DefaultObserver<List<Customer>>() {
            @Override
            public void onNext(List<Customer> customers) {
                mAllData = mViewModelConverter.convertCustomers(customers);
                processList(mAllData);
            }

            @Override
            public void onError(Throwable exception) {
                processError(exception);
            }
        }, null);
    }

    @Override
    public void destroy() {
        super.destroy();
        mGetCustomersInteractor.dispose();
    }

    public void onCustomerSelected(CustomerViewModel customerViewModel) {
        mView.openTableSelect(customerViewModel.getId());
    }

    public void onReloadClick() {
        loadData();
    }

    public void onSearch(String query) {
        mSearchDebouncer.onNext(query);
    }

    private void doSearch(String query) {
        if (TextUtils.isEmpty(query)) {
            processList(mAllData);
        } else {
            mSearchCustomersInteractor.execute(new DefaultObserver<List<Customer>>() {
                @Override
                public void onNext(List<Customer> customers) {
                    processList(mViewModelConverter.convertCustomers(customers));
                }

                @Override
                public void onError(Throwable exception) {
                    processError(exception);
                }
            }, new SearchCustomersInteractor.Params(query));
        }
    }

    private void processError(Throwable e) {
        if (e instanceof UnknownHostException) {
            //if we have NetworkException, then local data is empty we are unable to load remote data
            mView.showError(R.string.no_internet_error);
        } else {
            mView.showError(R.string.common_no_data);
        }
    }

    private void processList(List<CustomerViewModel> customers) {
        if (customers != null && customers.size() > 0) {
            mView.showData(customers);
        } else {
            mView.showEmpty();
        }
    }
}
