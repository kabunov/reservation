package com.kabunov.reservation.data.datasource.local.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class PreferencesManager {

    private Context mContext;

    @Inject
    PreferencesManager(@NonNull Context context) {
        mContext = context;
    }

    String getValue(final String fileName, final String key) {

        return getPreferences(fileName).getString(key, "");
    }

    void putValue(final String fileName, final String key, final Object value) {

        final SharedPreferences.Editor editor = getPreferences(fileName).edit();
        if (value instanceof Long) {
            editor.putLong(key, (long) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else {
            editor.putString(key, value.toString());
        }
        commit(editor);
    }


    void removeValue(final String fileName, final String key) {

        final SharedPreferences.Editor editor = getPreferences(fileName).edit();
        editor.remove(key);
        commit(editor);
    }

    private SharedPreferences getPreferences(final String fileName) {

        return mContext.getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    private static void commit(final SharedPreferences.Editor editor) {

        if (Build.VERSION_CODES.GINGERBREAD <= Build.VERSION.SDK_INT) {
            doAPI9StyleCommit(editor);
        } else {
            editor.commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static void doAPI9StyleCommit(final SharedPreferences.Editor editor) {
        editor.apply();
    }
}

