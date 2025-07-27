package com.moviepulse.movieApi.exceptions;

public class FileAlreadyExistException extends RuntimeException  {
    public FileAlreadyExistException(String message){
        super(message);
    }
}
