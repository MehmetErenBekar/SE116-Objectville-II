package com.objectville.model;

public class WaterPumpingStation extends UtilityProvider{
    public WaterPumpingStation(int row, int col) {
        super(row, col);
        this.type = "WATER";
    }
    @Override
    public char display(){
        return 'W';  //W means water
    }
}
