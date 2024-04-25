package com.nptien.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogAPIException extends RuntimeException {

    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BlogAPIException(String message1, HttpStatus httpStatus, String message) {
        super(message1);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String message;

}
