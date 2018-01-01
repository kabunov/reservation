package com.kabunov.reservation.ui.table.view;

public interface TableSelectView {

    void close();

    void showReservedMessage();

    void showReserveError();

    void showReserveSuccess();
}
