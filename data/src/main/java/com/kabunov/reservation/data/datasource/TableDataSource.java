package com.kabunov.reservation.data.datasource;

import java.util.List;

import io.reactivex.Observable;

public interface TableDataSource {

    Observable<List<Boolean>> getTables();

    Observable<Void> reserve(int tableId);

    Observable<Void> clearReservations();
}
