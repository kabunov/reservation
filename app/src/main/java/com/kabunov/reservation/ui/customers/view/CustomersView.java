package com.kabunov.reservation.ui.customers.view;

import com.kabunov.reservation.ui.base.BaseListView;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;

public interface CustomersView extends BaseListView<CustomerViewModel> {

    void openTableSelect(int customerId);
}
