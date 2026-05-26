package com.objectville.model;

public class PoliceStation extends ServiceProvider{
    public PoliceStation(int row,int col){
       super(row, col);
       this.type="SECURITY";
       this.radius=5;
    }
    @Override
    public char display(){
        return 'F'; //F means force
    }

}
