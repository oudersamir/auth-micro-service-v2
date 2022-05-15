package com.auth.micro.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class BusinessResourceException extends RuntimeException {

    private static final long serialVersionUID = -7625754983250836825L;
    private Date              timestamp;
    private Long              resourceId;
    private String            errorCode;
    private HttpStatus        status;

    public BusinessResourceException(String message) {
        super(message);
    }

    public BusinessResourceException(Date timestamp, Long resourceId, String errorCode, HttpStatus status) {
        super();
        this.timestamp  = timestamp;
        this.resourceId = resourceId;
        this.errorCode  = errorCode;
        this.status     = status;
    }

    public BusinessResourceException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public BusinessResourceException(Long resourceId, String errorCode, String message) {
        super(message);
        this.resourceId = resourceId;
        this.errorCode  = errorCode;
    }

    public BusinessResourceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessResourceException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status    = status;
    }

    public BusinessResourceException(Date timestamp, String errorCode, String message, HttpStatus status) {
        super(message);
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.status    = status;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
