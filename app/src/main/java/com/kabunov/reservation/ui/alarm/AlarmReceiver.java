package com.kabunov.reservation.ui.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Intent serviceIntent = AlarmService.createIntent(context);

        context.startService(serviceIntent);
    }
}
