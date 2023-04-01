package com.helloworld.learn.app.responses;

public class AccessTokenResponse {

    private String accessToken;
    private String userUuid;


    public AccessTokenResponse(String token, String userUuid) {
        this.accessToken = token;
        this.userUuid = userUuid;
    }

    public String getUserUuid() {
        return this.userUuid;
    }

    public void setUserUuid(String userId) {
        this.userUuid = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }
}
