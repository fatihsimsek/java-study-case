package com.readingisgood.ordermanagement.adapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CustomerCreateRequest {
    @ApiModelProperty(required = true, value = "Fullname")
    private String fullname;
    @ApiModelProperty(required = true, value = "Email")
    private String email;
    @ApiModelProperty(required = true, value = "Password")
    private String password;
    @ApiModelProperty(required = true, value = "RePassword")
    private String rePassword;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
