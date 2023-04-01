package com.helloworld.learn.app.models.auth;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private String accessToken;
    private String userCode;

    public JwtRequest(String encodedString, String userCode) {
        this.accessToken = encodedString;
        this.userCode = userCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserCode() {
        return this.userCode.substring(0,4);
    }

    public void setUserCode(String username) {
        this.userCode = username;
    }
}