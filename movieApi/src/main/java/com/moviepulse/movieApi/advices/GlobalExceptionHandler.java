package com.moviepulse.movieApi.advices;

import com.moviepulse.movieApi.exceptions.EmptyFileException;
import com.moviepulse.movieApi.exceptions.FileAlreadyExistException;
import com.moviepulse.movieApi.exceptions.GivenFileNotFoundException;
import com.moviepulse.movieApi.exceptions.MovieNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ApiError> handleMovieNotFoundException(MovieNotFoundException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GivenFileNotFoundException.class)
    public ResponseEntity<ApiError> handleGivenFileNotFoundException(GivenFileNotFoundException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<ApiError> handleEmptyFileException(EmptyFileException e, HttpServletRequest request){
        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileAlreadyExistException.class)
    public ResponseEntity<ApiError> handleFileAlreadyExistException(FileAlreadyExistException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.CONFLICT,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }
}
