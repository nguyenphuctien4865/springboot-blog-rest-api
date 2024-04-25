package com.nptien.blog.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nptien.blog.dtos.error.ErrrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handle specific exception
    @ExceptionHandler(ResourceNotFoundExceptionnn.class)
    public ResponseEntity<ErrrorDetails> handleResourceNotFoundException(ResourceNotFoundExceptionnn exception,
            WebRequest webRequest) {
        ErrrorDetails errorDetails = new ErrrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrrorDetails> handleBlogAPIExceptionn(BlogAPIException exception,
            WebRequest webRequest) {
        ErrrorDetails errorDetails = new ErrrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    // global exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrrorDetails> handleGlobalException(Exception exception,
            WebRequest webRequest) {
        ErrrorDetails errorDetails = new ErrrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
