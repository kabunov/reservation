package com.kabunov.reservation.ui.table.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kabunov.reservation.R;
import com.kabunov.reservation.ui.table.model.TableViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private List<TableViewModel> mItems;
    private Context mContext;
    private TablesAdapterListener mListener;

    TablesAdapter(Context context, TablesAdapterListener listener) {
        mContext = context;
        mListener = listener;

        setHasStableIds(true);

        mItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_table_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }

    public void setData(List<TableViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.table_list_item_root)
        ViewGroup mLayoutRoot;
        @BindView(R.id.table_list_item_header)
        TextView mHeaderText;
        @BindView(R.id.table_list_item_status)
        TextView mStatusText;

        ViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        void bind(TableViewModel item) {
            mHeaderText.setText(item.getTitle());
            mStatusText.setText(item.getStatus());
            mLayoutRoot.setBackgroundColor(ContextCompat.getColor(mContext, item.getBackgroundColorResourceId()));

            mLayoutRoot.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemSelected(item);
                }
            });
        }
    }

    public interface TablesAdapterListener {
        void onItemSelected(TableViewModel tableViewModel);
    }
}
