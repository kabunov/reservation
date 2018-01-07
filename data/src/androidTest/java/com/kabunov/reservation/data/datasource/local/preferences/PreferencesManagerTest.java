package com.kabunov.reservation.data.datasource.local.preferences;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PreferencesManagerTest {

    private static final String FILE_NAME = "FILE_NAME";
    private static final String KEY1 = "KEY1";
    private static final String KEY2 = "KEY2";

    private PreferencesManager mPreferencesManager;

    @Before
    public void setUp() {
        mPreferencesManager = new PreferencesManager(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testSaveReadValue() throws Exception {

        String str = "test";

        mPreferencesManager.putValue(FILE_NAME, KEY1, str);
        String savedStr = mPreferencesManager.getValue(FILE_NAME, KEY1);

        assertEquals(str, savedStr);
    }

    @Test
    public void testSaveReadDifferentValues() throws Exception {

        String str1 = "test1";
        String str2 = "test2";

        mPreferencesManager.putValue(FILE_NAME, KEY1, str1);
        mPreferencesManager.putValue(FILE_NAME, KEY2, str2);
        String savedStr1 = mPreferencesManager.getValue(FILE_NAME, KEY1);
        String savedStr2 = mPreferencesManager.getValue(FILE_NAME, KEY2);

        assertEquals(str1, savedStr1);
        assertEquals(str2, savedStr2);
        assertNotEquals(str1, savedStr2);
        assertNotEquals(str2, savedStr1);
    }

    @Test
    public void testRemoveValue() throws Exception {

        String str1 = "test1";

        mPreferencesManager.putValue(FILE_NAME, KEY1, str1);

        mPreferencesManager.removeValue(FILE_NAME, KEY1);

        String savedStr1 = mPreferencesManager.getValue(FILE_NAME, KEY1);

        assertEquals(savedStr1, "");
        assertNotEquals(str1, savedStr1);
    }

}