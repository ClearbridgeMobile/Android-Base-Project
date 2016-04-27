package com.clearbridgemobile.core.interfaces;

import com.clearbridgemobile.core.models.GpsModel;
import com.clearbridgemobile.core.models.ShareModel;

public interface ShareInterface {
    public void nativeShare(ShareModel shareModel);

    public void openMapWithLocation(GpsModel gpsModel);

    public void callPhoneNumber(String phone);

    public void openWebSite(String link);
}
