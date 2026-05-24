package com.objectville.model;

import com.objectville.interfaces.UtilityProducer;

public abstract class UtilityProvider extends Cell implements UtilityProducer {
    protected String type; //electricity , water or internet
    protected int capacity=100; // each provider generates 100 units per tick

    public UtilityProvider(int row,int col){
        super(row, col);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
