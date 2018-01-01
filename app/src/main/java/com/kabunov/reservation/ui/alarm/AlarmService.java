package com.kabunov.reservation.ui.alarm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.kabunov.reservation.application.App;
import com.kabunov.reservation.domain.interactor.ClearReservationsInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;

import javax.inject.Inject;


/**
 * Service to call Clear Reservations interactor
 */
public final class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Inject
    ClearReservationsInteractor mClearReservationsInteractor;

    @Inject
    AlarmHelper mAlarmHelper;

    static Intent createIntent(final Context context) {
        return new Intent(context, AlarmService.class);
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
