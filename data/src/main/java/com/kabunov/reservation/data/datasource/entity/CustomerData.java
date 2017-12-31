package com.kabunov.reservation.data.datasource.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(prefix = "m")
public class CustomerData {
    @SerializedName("id")
    private int mId;

    @SerializedName("customerFirstName")
    private String mFirstName;

    @SerializedName("customerLastName")
    private String mLastName;
}
