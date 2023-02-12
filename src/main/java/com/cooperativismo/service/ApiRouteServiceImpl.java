package com.cooperativismo.service;

import com.cooperativismo.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ApiRouteServiceImpl implements ApiRouteService {
    private final Environment environment;


    @Autowired
    public ApiRouteServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getAuthenticationServiceUrl() {
        return getHostUrl() + Constantes.AUTHENTICATE_URL;
    }

    @Override
    public String getHostUrl() {
        return environment.getProperty("api.host");
    }
}
