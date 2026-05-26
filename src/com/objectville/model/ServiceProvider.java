package com.objectville.model;

public abstract class ServiceProvider extends Cell {
    protected int radius; //how far this service reaches on the grid
    protected String type; //service type -> security, health, education

    public ServiceProvider(int row, int col){
        super(row, col);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
