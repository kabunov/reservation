package com.kabunov.reservation.ui.customers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(prefix = "m")
public class CustomerViewModel {
    private int mId;
    private String mFirstLetter;
    private String mName;
}
