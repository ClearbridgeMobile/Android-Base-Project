package com.clearbridgemobile.core.callbacks;

public interface NetworkCallback {
    public void onGotData(String data);

    public void onGotError(int code, String errorMsg);
}
