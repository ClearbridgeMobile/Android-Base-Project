package com.clearbridgemobile.baseapplication.implementations;

import android.content.Context;
import android.content.SharedPreferences;

import com.clearbridgemobile.core.interfaces.StorageInterface;

public class StorageImplementation implements StorageInterface {

    private static final String PREFERENCES_NAME = "PREF_LIB_STORAGE";
    private Context _context;
    private SharedPreferences _preferences;

    public StorageImplementation(Context context) {
        _context = context;
        _preferences = _context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveBoolean(String key, boolean val) {
        if (_preferences == null) {
            return;
        }
        SharedPreferences.Editor editor = _preferences.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    @Override
    public void saveInt(String key, int val) {
        if (_preferences == null) {
            return;
        }

        SharedPreferences.Editor editor = _preferences.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    @Override
    public void saveFloat(String key, float val) {
        if (_preferences == null) {
            return;
        }

        SharedPreferences.Editor editor = _preferences.edit();
        editor.putFloat(key, val);
        editor.commit();
    }

    @Override
    public void saveDouble(String key, double val) {
        if (_preferences == null) {
            return;
        }
    }

    @Override
    public void saveString(String key, String val) {
        if (_preferences == null) {
            return;
        }

        SharedPreferences.Editor editor = _preferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    @Override
    public void saveObject(String key, Object val) {
        if (_preferences == null) {
            return;
        }
    }

    @Override
    public boolean getBoolean(String key, boolean defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return _preferences.getBoolean(key, defaultVal);
    }

    @Override
    public int getInt(String key, int defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return _preferences.getInt(key, defaultVal);
    }

    @Override
    public float getFloat(String key, float defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return _preferences.getFloat(key, defaultVal);
    }

    @Override
    public double getDouble(String key, double defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return defaultVal;
    }

    @Override
    public String getString(String key, String defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return _preferences.getString(key, defaultVal);
    }

    @Override
    public Object getObject(String key, Object defaultVal) {
        if (_preferences == null) {
            return defaultVal;
        }

        return defaultVal;
    }

    @Override
    public void removeKey(String key) {
        if (_preferences == null) {
            return;
        }

        _preferences.edit().remove(key).commit();
    }
}

