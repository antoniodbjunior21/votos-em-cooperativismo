package com.cooperativismo.utils;

public class Constantes {
    public static final String API_V1_URL = "/api/v1/";
    public static final String AUTHENTICATE_URL = "autenticar";
    private static final String VALIDATE_CPF_URL = "https://user-info.herokuapp.com/users/";

    public static String getCpfValidatorUrl(String cpf){
        return VALIDATE_CPF_URL + cpf.trim();
    }

}
