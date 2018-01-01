package com.kabunov.reservation.ui.main.presenter;

import com.kabunov.reservation.ui.alarm.AlarmHelper;
import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.main.view.MainView;

import javax.inject.Inject;


public class MainPresenter extends BasePresenter<MainView> {

    private AlarmHelper mAlarmHelper;

    @Inject
    MainPresenter(AlarmHelper alarmHelper) {

        mAlarmHelper = alarmHelper;
    }

    public void onViewReady(boolean openedFirstTime) {
        if (openedFirstTime) {
            mAlarmHelper.scheduleClearReservations();
        }
    }

    public void onReserveClick() {
        mView.openCustomerSelect();
    }
}
