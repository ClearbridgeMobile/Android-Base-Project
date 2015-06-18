package com.clearbridgemobile.core.interfaces;

import java.util.Hashtable;

public interface NetworkInterface {
    public void makeGetRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID);
    public void makePostRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID);
    public void makePutRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID);
    public void makeDeleteRequest(String url, Hashtable<String, String> headers, Hashtable<String, Object> params, String requestID);
    public boolean isConnectedToNetwork();
}
