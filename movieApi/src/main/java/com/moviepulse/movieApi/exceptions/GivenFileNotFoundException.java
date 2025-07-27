package com.moviepulse.movieApi.exceptions;

public class GivenFileNotFoundException extends RuntimeException {
    public GivenFileNotFoundException(String message){
        super(message);
    }
}
