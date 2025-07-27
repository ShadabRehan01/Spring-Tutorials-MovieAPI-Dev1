package com.moviepulse.movieApi.advices;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    public LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;
    private String instance;

    public ApiError(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode, String instance) {
        this();
        this.error = error;
        this.statusCode = statusCode;
        this.instance = instance;
    }
}
