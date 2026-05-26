package com.objectville.exception;

public class InvalidMapFormatException extends RuntimeException{
    public InvalidMapFormatException(String message){
        super(message);
    }
}
