package com.kabunov.reservation.ui.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kabunov.reservation.R;
import com.kabunov.reservation.application.App;
import com.kabunov.reservation.ui.base.BaseActivity;
import com.kabunov.reservation.ui.customers.view.CustomersActivity;
import com.kabunov.reservation.ui.main.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    MainPresenter mPresenter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App.getAppComponent().injectMainActivity(this);

        setSupportActionBar(mToolbar);

        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @OnClick(R.id.main_reserve)
    void onReserveClick() {
        mPresenter.onReserveClick();
    }

    @Override
    public void openCustomerSelect() {
        CustomersActivity.start(this);
    }
}
