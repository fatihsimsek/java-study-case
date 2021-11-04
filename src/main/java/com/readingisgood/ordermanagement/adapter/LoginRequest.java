package com.readingisgood.ordermanagement.adapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginRequest {
    @ApiModelProperty(required = true, value = "Email")
    private String email;
    @ApiModelProperty(required = true, value = "Password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
