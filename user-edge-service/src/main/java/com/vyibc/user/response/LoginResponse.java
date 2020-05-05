package com.vyibc.user.response;

import groovy.transform.ToString;
import lombok.Data;

/**
 * Created by Michael on 2017/10/30.
 */
@Data
@ToString
public class LoginResponse extends Response {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

}
