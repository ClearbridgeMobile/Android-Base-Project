package com.clearbridgemobile.core.models;

/**
 * Created by nikitakramarovsky on 15-04-07.
 */
public class ShareModel {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String msg;
    private String imagePath;
}
