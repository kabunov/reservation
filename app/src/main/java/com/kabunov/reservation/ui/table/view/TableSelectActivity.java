package com.kabunov.reservation.ui.table.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.kabunov.reservation.R;
import com.kabunov.reservation.application.App;
import com.kabunov.reservation.ui.base.BaseActivity;
import com.kabunov.reservation.ui.main.view.MainActivity;
import com.kabunov.reservation.ui.table.model.TableViewModel;
import com.kabunov.reservation.ui.table.presenter.TableSelectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableSelectActivity extends BaseActivity implements TableSelectView, TableListFragment.TableListFragmentListener {

    private static final String EXTRA_CUSTOMER_ID = "EXTRA_CUSTOMER_ID";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    TableSelectPresenter mPresenter;

    private TablesAdapter mAdapter;

    public static void start(Activity activity, int customerId) {
        Intent intent = new Intent(activity, TableSelectActivity.class);
        intent.putExtra(EXTRA_CUSTOMER_ID, customerId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_select);
        ButterKnife.bind(this);

        App.getAppComponent().injectTablesActivity(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       int consumerId = getIntent().getIntExtra(EXTRA_CUSTOMER_ID, 0);

        mPresenter.setView(this);
        mPresenter.setData(consumerId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void close() {
        MainActivity.start(this);
    }

    @Override
    public void showReservedMessage() {
        Toast.makeText(this, R.string.tables_reserved_warning, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReserveError() {
        Toast.makeText(this, R.string.tables_reserve_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReserveSuccess() {
        Toast.makeText(this, R.string.tables_reserve_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTableClick(TableViewModel tableViewModel) {
        mPresenter.onTableSelected(tableViewModel);
    }
}
