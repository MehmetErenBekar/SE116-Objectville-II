package com.objectville.interfaces;

//utility providers implement this interface to generate resources
public interface UtilityProducer {
    String getType();
    int getCapacity();
}
