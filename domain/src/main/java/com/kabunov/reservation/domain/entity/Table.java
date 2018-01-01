package com.kabunov.reservation.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(prefix = "m")
public class Table {

    private int mId;
    private boolean mIsReserved;
}
