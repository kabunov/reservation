package com.kabunov.reservation.ui.table.presenter;

import com.kabunov.reservation.domain.interactor.ReserveTableInteractor;
import com.kabunov.reservation.domain.interactor.base.DefaultObserver;
import com.kabunov.reservation.ui.base.BasePresenter;
import com.kabunov.reservation.ui.table.model.TableViewModel;
import com.kabunov.reservation.ui.table.view.TableSelectView;

import javax.inject.Inject;

public class TableSelectPresenter extends BasePresenter<TableSelectView> {

    private ReserveTableInteractor mReserveTableInteractor;
    private int mConsumerId;

    @Inject
    TableSelectPresenter(ReserveTableInteractor reserveTableInteractor) {
        mReserveTableInteractor = reserveTableInteractor;
    }

    public void setData(int consumerId) {
        mConsumerId = consumerId;
    }

    public void onTableSelected(TableViewModel tableViewModel) {
        if (tableViewModel.isReserved()) {
            mView.showReservedMessage();
        } else {
            mReserveTableInteractor.execute(new ReserveTableObserver(), new ReserveTableInteractor.Params(mConsumerId, tableViewModel.getId()));
            mView.close();
        }
    }

    private class ReserveTableObserver extends DefaultObserver<Void> {

        @Override
        public void onError(Throwable e) {
            mView.showReserveError();
        }

        @Override
        public void onComplete() {
            mView.showReserveSuccess();
            mView.close();
        }
    }
}
