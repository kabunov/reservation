package com.kabunov.reservation.ui.table.presenter;

import android.content.Context;

import com.kabunov.reservation.R;
import com.kabunov.reservation.domain.entity.Table;
import com.kabunov.reservation.ui.table.model.TableViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TableViewModelConverter {

    private Context mContext;

    @Inject
    TableViewModelConverter(Context context) {
        mContext = context;
    }

    public TableViewModel convertTable(Table table) {

        return new TableViewModel(
                table.getId(),
                table.isReserved(),
                String.format("#%d", table.getId() + 1),
                mContext.getString(table.isReserved() ? R.string.tables_unavailable : R.string.tables_available),
                table.isReserved() ? R.color.table_reserved : R.color.colorAccent
        );
    }

    public List<TableViewModel> convertTables(List<Table> tables) {

        List<TableViewModel> res = new ArrayList<>();

        for (Table table : tables) {
            res.add(convertTable(table));
        }

        return res;
    }
}
