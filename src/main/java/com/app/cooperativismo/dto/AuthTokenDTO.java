package com.app.cooperativismo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthTokenDTO {

    public String accessToken;
    public AssociadoDTO userData;

    public AuthTokenDTO() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AssociadoDTO getUserData() {
        return userData;
    }

    public void setUserData(AssociadoDTO userData) {
        this.userData = userData;
    }
}
