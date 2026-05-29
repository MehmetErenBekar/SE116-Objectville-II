package com.objectville.model;

public class School extends ServiceProvider {
    public School(int row,int col){
        super(row, col);
        this.type="EDUCATION";
        this.radius=4;
    }
    @Override
    public char display(){
        return 'S'; //S means school
    }
}
