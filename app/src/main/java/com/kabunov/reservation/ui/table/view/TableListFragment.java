package com.kabunov.reservation.ui.table.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kabunov.reservation.R;
import com.kabunov.reservation.application.App;
import com.kabunov.reservation.ui.table.model.TableViewModel;
import com.kabunov.reservation.ui.table.presenter.TableListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment to show list of tables
 */
public class TableListFragment extends Fragment implements TableListView {

    @BindView(R.id.tables_list)
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
    TableListPresenter mPresenter;

    private TableListFragmentListener mListener;

    private TablesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().injectTableListFragment(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(getActivity(), R.layout.layout_table_list_item);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridLayoutHelper.calculateNoOfColumns()));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(gridLayoutHelper.calculateSpacing()));
        mAdapter = new TablesAdapter(getActivity(), new TablesAdapter.TablesAdapterListener() {
            @Override
            public void onItemSelected(TableViewModel tableViewModel) {
                if (mListener != null) {
                    mListener.onTableClick(tableViewModel);
                }
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TableListFragmentListener) {
            mListener = (TableListFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
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
    public void showData(List<TableViewModel> data) {
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mTryAgainView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeToRefreshView.setRefreshing(false);

        mAdapter.setData(data);
    }

    @OnClick(R.id.try_again_button)
    void onTryAgainClick() {
        mPresenter.onReloadClick();
    }

    public interface TableListFragmentListener {
        void onTableClick(TableViewModel tableViewModel);
    }

    class GridLayoutHelper {
        private int mWidth;
        private int mRemaining;
        private DisplayMetrics mDisplayMetrics;

        GridLayoutHelper(Context context, int viewId) {

            View view = View.inflate(context, viewId, null);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mWidth = view.getMeasuredWidth();
            mDisplayMetrics = context.getResources().getDisplayMetrics();
        }

        int calculateNoOfColumns() {

            int numberOfColumns = mDisplayMetrics.widthPixels / mWidth;
            mRemaining = mDisplayMetrics.widthPixels - (numberOfColumns * mWidth);
            if (mRemaining / (2 * numberOfColumns) < 15) {
                numberOfColumns--;
                mRemaining = mDisplayMetrics.widthPixels - (numberOfColumns * mWidth);
            }
            return numberOfColumns;
        }

        int calculateSpacing() {

            int numberOfColumns = calculateNoOfColumns();
            return mRemaining / (2 * numberOfColumns);
        }
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }
    }
}
