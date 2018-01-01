package com.kabunov.reservation.ui.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Date;

import javax.inject.Inject;

/**
 * Helper to schedule repeated action.
 * By requirements the app needs to clear reservations every 15 min, even is the app is closed
 */
public class AlarmHelper {

    private static final String ALARM_ACTION = "com.kabunov.reservation.ALARM_ACTION";
    private static final int ALARM_DELAY_MIN = 15;

    private Context mContext;

    @Inject
    AlarmHelper(Context context) {
        mContext = context;
    }

    public void scheduleClearReservations() {
        long time = new Date().getTime() + ALARM_DELAY_MIN * 60 * 1000;

        final Intent broadcastIntent = new Intent(ALARM_ACTION);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 123, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        final int ALARM_TYPE = AlarmManager.RTC_WAKEUP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(ALARM_TYPE, time, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setExact(ALARM_TYPE, time, pendingIntent);
        } else {
            alarmManager.set(ALARM_TYPE, time, pendingIntent);
        }
    }
}
