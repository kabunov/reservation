package com.kabunov.reservation.ui.customers.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kabunov.reservation.R;
import com.kabunov.reservation.ui.customers.model.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> {

    private List<CustomerViewModel> mItems;
    private CustomerAdapterListener mListener;

    CustomersAdapter(final CustomerAdapterListener listener) {
        mListener = listener;

        setHasStableIds(true);

        mItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_customer_list_item, parent, false);

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

    public void setData(List<CustomerViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.customer_list_item_root)
        ViewGroup mLayoutRoot;
        @BindView(R.id.customer_list_item_icon)
        ListItemCircleIcon mIcon;
        @BindView(R.id.customer_list_item_header)
        TextView mHeaderText;

        ViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        void bind(CustomerViewModel item) {
            mIcon.setLetter(item.getFirstLetter());
            mHeaderText.setText(item.getName());

            mLayoutRoot.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemSelected(item);
                }
            });
        }
    }

    public interface CustomerAdapterListener {
        void onItemSelected(CustomerViewModel customerViewModel);
    }
}
