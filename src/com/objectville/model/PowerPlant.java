package com.objectville.model;

public class PowerPlant extends UtilityProvider{
    public PowerPlant(int row, int col) {
        super(row, col);
        this.type = "ELECTRICITY";
    }
    @Override
    public char display(){
        return 'P';//P means power
    }
}
