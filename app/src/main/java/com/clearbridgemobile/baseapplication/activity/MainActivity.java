package com.clearbridgemobile.baseapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.clearbridgemobile.baseapplication.R;
import com.clearbridgemobile.baseapplication.implementations.GpsImplementation;
import com.clearbridgemobile.baseapplication.implementations.NavigationImplementation;
import com.clearbridgemobile.baseapplication.implementations.NetworkImplementation;
import com.clearbridgemobile.baseapplication.implementations.ShareImplementation;
import com.clearbridgemobile.baseapplication.implementations.StorageImplementation;
import com.clearbridgemobile.core.CoreLib;
import com.clearbridgemobile.core.enums.EnvironmentType;
import com.clearbridgemobile.core.enums.InterfaceId;
import com.clearbridgemobile.core.managers.InterfaceManager;
import com.clearbridgemobile.core.managers.NetworkManager;


public class MainActivity extends Activity {

    private CoreLib coreLib;
    private NetworkManager networkManager;
    private InterfaceManager interfaceManager;

    private NetworkImplementation networkImplementation;
    private NavigationImplementation navigationImplementation;
    private StorageImplementation storageImplementation;
    private GpsImplementation gpsImplementation;
    private ShareImplementation shareImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init () {
        //Initialize the app:
        if (coreLib == null)
        {
            //Get a reference to Raizen Lib:
            coreLib = CoreLib.getInstance();
        }

        if (networkManager == null)
        {
            //Get a reference to the Network Manager:
            networkManager = coreLib.getNetworkManager();
        }

        if (interfaceManager == null)
        {
            //Get a reference to the Interface Manager:
            interfaceManager = coreLib.getInterfaceManager();
        }

        if (networkImplementation == null)
        {
            //Create a new NetworkImplementation:
            networkImplementation = new NetworkImplementation (getApplicationContext());

            networkImplementation.setNetworkManager(networkManager);
            interfaceManager.registerInterface(InterfaceId.NETWORK, networkImplementation);
        }

        if(navigationImplementation == null){
            navigationImplementation = new NavigationImplementation (this);
            interfaceManager.registerInterface(InterfaceId.NAVIGATION, navigationImplementation);
        }

        if(storageImplementation == null){
            storageImplementation = new StorageImplementation (this);
            interfaceManager.registerInterface(InterfaceId.STORAGE, storageImplementation);
        }

        if(gpsImplementation == null)
        {
            gpsImplementation = new GpsImplementation();
            interfaceManager.registerInterface(InterfaceId.GPS, gpsImplementation);
        }

        if(shareImplementation == null)
        {
            shareImplementation = new ShareImplementation(this);
            interfaceManager.registerInterface(InterfaceId.SHARE, shareImplementation);
        }

        coreLib.initLib(EnvironmentType.DEV);
    }
}
