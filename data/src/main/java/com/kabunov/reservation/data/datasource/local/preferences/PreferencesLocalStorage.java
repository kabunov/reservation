package com.kabunov.reservation.data.datasource.local.preferences;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.kabunov.reservation.data.datasource.entity.CustomerData;
import com.kabunov.reservation.data.datasource.local.LocalStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class PreferencesLocalStorage implements LocalStorage {

    private static final String FILE_NAME = "reservation";
    private static final String KEY_TABLES = "tables";
    private static final String KEY_CUSTOMERS = "customers";

    private final PreferencesManager mPreferencesManager;
    private final Gson mGson;

    @Inject
    public PreferencesLocalStorage(@NonNull PreferencesManager preferencesManager) {
        mPreferencesManager = preferencesManager;
        mGson = new Gson();
    }

    @Override
    public List<Boolean> getTables() {
        String value = mPreferencesManager.getValue(FILE_NAME, KEY_TABLES);
        return new ArrayList<>(Arrays.asList(mGson.fromJson(value, Boolean[].class)));
    }

    @Override
    public void saveTables(List<Boolean> tables) {
        mPreferencesManager.putValue(FILE_NAME, KEY_TABLES, mGson.toJson(tables.toArray()));
    }

    @Override
    public List<CustomerData> getCustomers() {
        String value = mPreferencesManager.getValue(FILE_NAME, KEY_CUSTOMERS);
        return new ArrayList<>(Arrays.asList(mGson.fromJson(value, CustomerData[].class)));
    }

    @Override
    public void saveCustomers(List<CustomerData> customers) {
        mPreferencesManager.putValue(FILE_NAME, KEY_CUSTOMERS, mGson.toJson(customers.toArray()));
    }
}
