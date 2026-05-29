package com.objectville.model;

public class InternetHub extends UtilityProvider{
    public InternetHub(int row, int col) {
        super(row, col);
        this.type= "INTERNET";
    }
    @Override
    public char display(){
        return 'T'; // T means internet
    }
}
