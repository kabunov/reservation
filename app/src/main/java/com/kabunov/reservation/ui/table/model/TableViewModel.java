package com.kabunov.reservation.ui.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(prefix = "m")
public class TableViewModel {
    private int mId;
    private boolean mReserved;
    private String mTitle;
    private String mStatus;
    private int mBackgroundColorResourceId;
}
