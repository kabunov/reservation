package com.kabunov.reservation.di;

import com.kabunov.reservation.ui.alarm.AlarmService;
import com.kabunov.reservation.ui.customers.view.CustomersActivity;
import com.kabunov.reservation.ui.main.view.MainActivity;
import com.kabunov.reservation.ui.table.view.TableListFragment;
import com.kabunov.reservation.ui.table.view.TableSelectActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void injectMainActivity(MainActivity activity);

    void injectCustomersActivity(CustomersActivity activity);

    void injectTablesActivity(TableSelectActivity activity);

    void injectTableListFragment(TableListFragment fragment);

    void injectAlarmService(AlarmService service);
}
