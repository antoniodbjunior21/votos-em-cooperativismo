package com.cooperativismo.utils;

import javax.servlet.http.HttpServletRequest;

public class Constants {
    public static final String API_V1_URL = "/api/v1/";
    public static final String AUTHENTICATE_URL = "autenticar";
    public static final String VALIDATE_CPF_URL = "https://user-info.herokuapp.com/users/{cpf}";

    public static String getAuthenticateURL(HttpServletRequest request){
        return request.getRequestURL().toString() + AUTHENTICATE_URL;
    }

}
