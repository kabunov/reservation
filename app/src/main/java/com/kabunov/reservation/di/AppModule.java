package com.kabunov.reservation.di;

import android.content.Context;


import com.kabunov.reservation.application.UIThread;
import com.kabunov.reservation.domain.executor.BackgroundThreadExecutor;
import com.kabunov.reservation.domain.executor.PostExecutionThread;
import com.kabunov.reservation.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(BackgroundThreadExecutor backgroundThreadExecutor) {
        return backgroundThreadExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
