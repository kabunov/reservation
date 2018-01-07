package com.kabunov.reservation.data.datasource.local.preferences;

import com.kabunov.reservation.data.datasource.local.LocalStorage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PreferencesLocalStorageUnitTest {

    private static final String FILE_NAME = "reservation";
    private static final String KEY_TABLES = "tables";
    private static final String KEY_CUSTOMERS = "customers";

    private LocalStorage mLocalStorage;

    @Mock
    private PreferencesManager mPreferencesManager;

    @Before
    public void init() {
        mLocalStorage = new PreferencesLocalStorage(mPreferencesManager);

        given(mPreferencesManager.getValue(FILE_NAME, KEY_CUSTOMERS)).willReturn("[]");
        given(mPreferencesManager.getValue(FILE_NAME, KEY_TABLES)).willReturn("[]");
        doNothing().when(mPreferencesManager).putValue(eq(FILE_NAME), eq(KEY_CUSTOMERS), any());
        doNothing().when(mPreferencesManager).putValue(eq(FILE_NAME), eq(KEY_TABLES), any());
    }

    @Test
    public void testGetCustomers() throws Exception {
        mLocalStorage.getCustomers();
        verify(mPreferencesManager).getValue(FILE_NAME, KEY_CUSTOMERS);
    }

    @Test
    public void testGetTables() throws Exception {
        mLocalStorage.getTables();
        verify(mPreferencesManager).getValue(FILE_NAME, KEY_TABLES);
    }

    @Test
    public void testSaveCustomers() throws Exception {
        mLocalStorage.saveCustomers(new ArrayList<>());
        verify(mPreferencesManager).putValue(eq(FILE_NAME), eq(KEY_CUSTOMERS), any());
    }

    @Test
    public void testSaveTables() throws Exception {
        mLocalStorage.saveTables(new ArrayList<>());
        verify(mPreferencesManager).putValue(eq(FILE_NAME), eq(KEY_TABLES), any());
    }
}