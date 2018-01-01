package com.kabunov.reservation.ui.customers.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kabunov.reservation.R;
import com.kabunov.reservation.application.App;
import com.kabunov.reservation.ui.base.BaseActivity;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;
import com.kabunov.reservation.ui.customers.presenter.CustomersPresenter;
import com.kabunov.reservation.ui.table.view.TableSelectActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomersActivity extends BaseActivity implements CustomersView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.customers_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeToRefreshView;
    @BindView(R.id.try_again_container)
    View mTryAgainView;
    @BindView(R.id.try_again_message)
    TextView mTryAgainMessageView;
    @BindView(R.id.empty_state_layout)
    View mEmptyView;
    @BindView(R.id.global_progress)
    View mLoadingView;

    @Inject
    CustomersPresenter mPresenter;

    private CustomersAdapter mAdapter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CustomersActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        ButterKnife.bind(this);

        App.getAppComponent().injectCustomersActivity(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CustomersAdapter(new CustomersAdapter.CustomerAdapterListener() {
            @Override
            public void onItemSelected(CustomerViewModel customerViewModel) {
                mPresenter.onCustomerSelected(customerViewModel);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSwipeToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onReloadClick();
            }
        });

        mPresenter.setView(this);
        mPresenter.onViewReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @OnClick(R.id.try_again_button)
    void onTryAgainClick() {
        mPresenter.onReloadClick();
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int messageResourceId) {
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mTryAgainView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mSwipeToRefreshView.setRefreshing(false);

        mTryAgainMessageView.setText(messageResourceId);
    }

    @Override
    public void showEmpty() {
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mTryAgainView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mSwipeToRefreshView.setRefreshing(false);
    }

    @Override
    public void showData(List<CustomerViewModel> data) {
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mTryAgainView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeToRefreshView.setRefreshing(false);

        mAdapter.setData(data);
    }

    @Override
    public void openTableSelect(int customerId) {
        TableSelectActivity.start(this, customerId);
    }
}
