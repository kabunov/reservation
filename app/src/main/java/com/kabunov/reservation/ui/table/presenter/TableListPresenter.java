package com.kabunov.reservation.ui.table.presenter;

import com.kabunov.reservation.R;
import com.kabunov.reservation.domain.entity.Table;
import com.kabunov.reservation.domain.interactor.GetTablesInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;
import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.table.view.TableListView;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

public class TableListPresenter extends BasePresenter<TableListView> {

    private GetTablesInteractor mGetTablesInteractor;
    private TableViewModelConverter mViewModelConverter;

    @Inject
    TableListPresenter(GetTablesInteractor getTablesInteractor,
                       TableViewModelConverter viewModelConverter) {
        mGetTablesInteractor = getTablesInteractor;
        mViewModelConverter = viewModelConverter;
    }

    public void onViewReady() {
        loadData();
    }

    @Override
    public void destroy() {
        super.destroy();
        mGetTablesInteractor.dispose();
    }

    public void onReloadClick() {
        loadData();
    }

    private void loadData() {
        mView.showLoading();
        mGetTablesInteractor.execute(new GetTablesObserver(), null);
    }

    private class GetTablesObserver extends DefaultObserver<List<Table>> {

        @Override
        public void onError(Throwable e) {
            if (e instanceof UnknownHostException) {
                //if we have NetworkException, then local data is empty we are unable to load remote data
                mView.showError(R.string.no_internet_error);
            } else {
                mView.showError(R.string.common_no_data);
            }
        }

        @Override
        public void onNext(List<Table> tables) {
            if (tables != null && tables.size() > 0) {
                mView.showData(mViewModelConverter.convertTables(tables));
            } else {
                mView.showEmpty();
            }
        }
    }
}
