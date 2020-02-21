package com.sf.restapi.client.model;

import lombok.Data;

@Data
public class LoginResponse {
    private Boolean loginSuccess;
    private String loginFaultMessage;
    private String authToken;
}
