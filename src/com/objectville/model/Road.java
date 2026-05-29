package com.objectville.model;

public class Road extends Cell{
    public Road(int row, int col) {
        super(row, col);
    }
    //roads dont consume anything just pass utilities along
    @Override
    public char display(){
        return 'R';    //R means road
    }
}
