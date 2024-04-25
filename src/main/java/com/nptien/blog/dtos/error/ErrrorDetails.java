package com.nptien.blog.dtos.error;

import java.util.Date;

public class ErrrorDetails {
    
    private String message;
    private String details;
    private Date timestamp;
    private String errorCode;

    public ErrrorDetails(Date timestamp,String message, String details ) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }
    
}
