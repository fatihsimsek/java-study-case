package com.readingisgood.ordermanagement.adapter;

public class LoginResponse extends ApiResponse {

    public LoginResponse(boolean isSuccess, String message) {
        super(isSuccess);
        this.setMessage(message);
    }

    public LoginResponse(String token) {
        super(true);
        this.setToken(token);
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
