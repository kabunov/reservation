package com.kabunov.reservation.data.datasource.remote;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.TableDataSource;
import com.kabunov.reservation.data.datasource.remote.api.RestApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteTableDataSource implements TableDataSource {

    private RestApi mRestApi;

    @Inject
    public RemoteTableDataSource(@NonNull RestApi restApi) {
        mRestApi = restApi;
    }

    @Override
    public Observable<List<Boolean>> getTables() {
        return mRestApi.getTables();
    }

    @Override
    public Observable<Void> reserve(int tableId) {
        throw new UnsupportedOperationException("Remote operation is not available");
    }

    @Override
    public Observable<Void> clearReservations() {
        throw new UnsupportedOperationException("Remote operation is not available");
    }
}
