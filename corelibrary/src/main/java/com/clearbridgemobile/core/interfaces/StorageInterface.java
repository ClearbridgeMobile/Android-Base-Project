package com.clearbridgemobile.core.interfaces;

public interface StorageInterface {
    public void saveBoolean(String key, boolean val);
    public void saveInt(String key, int val);
    public void saveFloat(String key, float val);
    public void saveDouble(String key, double val);
    public void saveString(String key, String val);
    public void saveObject(String key, Object val); // not sure if this will be used

    public boolean getBoolean(String key, boolean defaultVal);
    public int getInt(String key, int defaultVal);
    public float getFloat(String key, float defaultVal);
    public double getDouble(String key, double defaultVal);
    public String getString(String key, String defaultVal);
    public Object getObject(String key, Object defaultVal); // not sure if this will be used

    public void removeKey(String key);
}
