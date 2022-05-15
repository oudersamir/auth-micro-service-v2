package com.auth.micro.responses;


public enum ErrorMessageResponse {

    MISSING_REQUIRED_FIELD("Missing required field."),
    RECORD_ALREADY_EXISTS("Record already exist."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    NO_RECORD_FOUND("Record with provided id is not found.");

    private String errorMessage;

    private ErrorMessageResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
