package com.objectville.interfaces;

//which zones consume utilities implement this interface
public interface UtilityConsumer {
    int getDemand();
    void adjustDemand();
    void reset();
}
