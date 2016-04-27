package com.clearbridgemobile.core;

import com.clearbridgemobile.core.enums.EnvironmentType;
import com.clearbridgemobile.core.managers.InterfaceManager;
import com.clearbridgemobile.core.managers.NetworkManager;
import com.google.gson.Gson;
import com.google.j2objc.annotations.ObjectiveCName;

public class CoreLib {
    private InterfaceManager interfaceManager;
    private NetworkManager networkManager;
    private Gson gson;

    private static CoreLib instance;

    private CoreLib() {
        networkManager = new NetworkManager();
        interfaceManager = new InterfaceManager();
        gson = new Gson();
    }

    public static CoreLib getInstance() {
        if (instance == null) {
            instance = new CoreLib();
        }
        return instance;
    }

    @ObjectiveCName(value = "initLib:(EnvironmentTypeEnum *)env")
    public void initLib(EnvironmentType env) {
        networkManager.setEnvironmentType(env);
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public InterfaceManager getInterfaceManager() {
        return interfaceManager;
    }

    public Gson getGson() {
        return gson;
    }
}
