package com.kabunov.reservation.ui.base;

import java.util.List;

public interface BaseListView<T> {

    void showLoading();

    void showError(int messageResourceId);

    void showEmpty();

    void showData(List<T> data);
}