package com.kabunov.reservation.domain.repository;

import com.kabunov.reservation.domain.entity.Table;

import java.util.List;

import io.reactivex.Observable;

public interface TableRepository {

    Observable<List<Table>> getTables();

    Observable<Table> reserve(int tableId);

    Observable<Void> clearReservations();
}
