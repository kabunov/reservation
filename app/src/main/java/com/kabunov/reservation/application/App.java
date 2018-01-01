package com.kabunov.reservation.application;

import android.app.Application;

import com.kabunov.reservation.di.AppComponent;
import com.kabunov.reservation.di.AppModule;
import com.kabunov.reservation.di.DaggerAppComponent;


public class App extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
    }

    private void initComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}