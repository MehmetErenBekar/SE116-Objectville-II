package com.objectville.model;

public class EmptyCell extends Cell{
    public EmptyCell(int row, int col) {
        super(row, col);
    }
    //empty cells block everything nothing passes through
    @Override
    public char display(){
        return 'E';  //E means empty
    }
}
