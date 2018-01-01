package com.kabunov.reservation.ui.customers.presenter;

import com.kabunov.reservation.R;
import com.kabunov.reservation.domain.entity.Customer;
import com.kabunov.reservation.domain.interactor.GetCustomersInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;
import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;
import com.kabunov.reservation.ui.customers.view.CustomersView;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

public class CustomersPresenter extends BasePresenter<CustomersView> {

    private GetCustomersInteractor mGetCustomersInteractor;
    private CustomerViewModelConverter mViewModelConverter;

    @Inject
    CustomersPresenter(GetCustomersInteractor getCustomersInteractor,
                       CustomerViewModelConverter viewModelConverter) {
        mGetCustomersInteractor = getCustomersInteractor;
        mViewModelConverter = viewModelConverter;
    }

    public void onViewReady() {
        loadData();
    }

    private void loadData() {
        mView.showLoading();
        mGetCustomersInteractor.execute(new GetCustomersObserver(), null);
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

    private class GetCustomersObserver extends DefaultObserver<List<Customer>> {

        @Override
        public void onError(Throwable e) {
            if (e instanceof UnknownHostException) {
                //if we have NetworkException, then local data is empty we are unable to load remote data
                mView.showError(R.string.no_internet_error);
            } else {
                mView.showError(R.string.common_no_data);
            }
        }

        @Override
        public void onNext(List<Customer> customers) {
            if (customers != null && customers.size() > 0) {
                mView.showData(mViewModelConverter.convertCustomers(customers));
            } else {
                mView.showEmpty();
            }
        }
    }
}
