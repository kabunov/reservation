package com.kabunov.reservation.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(prefix = "m")
public class Customer {

    private int mId;
    private String mFirstName;
    private String mLastName;
}
