package com.kabunov.reservation.data.datasource.local;

import android.support.annotation.NonNull;

import com.kabunov.reservation.data.datasource.TableDataSource;
import com.kabunov.reservation.data.exception.EmptyLocalStorageException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalTableDataSource implements TableDataSource {

    private LocalStorage mLocalStorage;

    @Inject
    public LocalTableDataSource(@NonNull LocalStorage localStorage) {
        mLocalStorage = localStorage;
    }

    @Override
    public Observable<List<Boolean>> getTables() {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(mLocalStorage.getTables());
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(new EmptyLocalStorageException());
            }
        });
    }

    @Override
    public Observable<Void> reserve(int consumerId, int tableId) {
        return Observable.create(subscriber -> {
            try {
                List<Boolean> tables = mLocalStorage.getTables();
                tables.set(tableId, false);
                mLocalStorage.saveTables(tables);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    @Override
    public Observable<Void> clearReservations() {
        return Observable.create(subscriber -> {
            try {
                List<Boolean> tables = mLocalStorage.getTables();
                for (int i = 0; i < tables.size(); i++) {
                    tables.set(i, true);
                }
                mLocalStorage.saveTables(tables);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
