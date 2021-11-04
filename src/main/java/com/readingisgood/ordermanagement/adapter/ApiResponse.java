package com.readingisgood.ordermanagement.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse {
    private boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ApiResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ApiResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
