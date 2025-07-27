package com.moviepulse.movieApi.exceptions;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String message){
        super(message);
    }
}
