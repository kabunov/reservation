package com.kabunov.reservation.ui.alarm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.kabunov.reservation.application.App;
import com.kabunov.reservation.domain.interactor.ClearReservationsInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;

import java.util.Timer;

import javax.inject.Inject;


public final class AlarmService extends IntentService {

    private Timer mCountDownTimer = new Timer();

    public AlarmService() {
        super("AlarmService");
    }

    @Inject
    ClearReservationsInteractor mClearReservationsInteractor;

    @Inject
    AlarmHelper mAlarmHelper;

    static Intent createIntent(final Context context) {
        final Intent intent = new Intent(context, AlarmService.class);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getAppComponent().injectAlarmService(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        mClearReservationsInteractor.execute(new DefaultObserver<Void>() {
            @Override
            public void onComplete() {
                mAlarmHelper.scheduleClearReservations();
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
            }
        }, null);
    }
}
