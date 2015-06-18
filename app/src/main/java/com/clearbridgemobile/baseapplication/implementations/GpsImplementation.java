package com.clearbridgemobile.baseapplication.implementations;

import android.location.Location;

import com.clearbridgemobile.baseapplication.utils.GpsManager;
import com.clearbridgemobile.core.interfaces.GpsInterface;
import com.clearbridgemobile.core.models.GpsModel;

public class GpsImplementation implements GpsInterface {

    /**
     * Returns last location of the device.
     * @return
     */
    @Override
    public GpsModel getLastLocation() {

        Location location = GpsManager.getInstance().getLastLocation();

        if(location != null)
        {
            GpsModel gpsModel = new GpsModel();
            gpsModel.setLatitude(String.valueOf(location.getLatitude()));
            gpsModel.setLongitude(String.valueOf(location.getLongitude()));

            return gpsModel;
        }

        return null;
    }
}
