package com.clearbridgemobile.core.managers;

import com.clearbridgemobile.core.enums.InterfaceId;
import com.google.j2objc.annotations.ObjectiveCName;

import java.util.Hashtable;

public class InterfaceManager {

    private Hashtable<InterfaceId, Object> interfacesMap;

    public InterfaceManager()
    {
        interfacesMap = new Hashtable<>();
    }

    /**
     * Register the implementation of the interface by id.
     *
     * @param interfaceId The id of the interface.
     * @param implementation The implementation of the interface.
     */
    @ObjectiveCName(value = "registerInterface:(InterfaceIdEnum*)interfaceId withImplementation:(id)implementation")
    public void registerInterface(InterfaceId interfaceId, Object implementation) {
        if(interfaceId == null)
        {
            throw new NullPointerException("Interface id should not be null.");
        }

        if(implementation == null)
        {
            throw new NullPointerException("Interface implementation should not be null.");
        }
        interfacesMap.put(interfaceId, implementation);
    }

    /**
     * Get the interface implementation for id.
     *
     * @param interfaceId The id of the interface.
     * @return The implementation of the interface.
     */
    @ObjectiveCName(value = "getInterface:(InterfaceIdEnum*)interfaceId")
    public Object getInterface(InterfaceId interfaceId)
    {
        Object implementation = interfacesMap.get(interfaceId);

        if(implementation == null)
        {
            throw new NullPointerException("Interface implementation for " + interfaceId + " is null.");
        }

        return implementation;
    }
}
