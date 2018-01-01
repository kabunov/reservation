package com.kabunov.reservation.ui.main.presenter;

import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.main.view.MainView;

import javax.inject.Inject;


public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    MainPresenter() {
    }

    public void onReserveClick() {
        mView.openCustomerSelect();
    }
}
