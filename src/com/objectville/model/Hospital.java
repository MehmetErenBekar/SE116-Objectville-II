package com.objectville.model;

public class Hospital extends ServiceProvider{
    public Hospital(int row, int col){
        super(row, col);
        this.type="HEALTH";
        this.radius=3;
    }
    @Override
    public char display(){
        return 'D'; //D means meDical
    }
}
