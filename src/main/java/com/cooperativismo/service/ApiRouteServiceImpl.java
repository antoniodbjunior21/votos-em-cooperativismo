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
    public String getHostUrl() {
        return environment.getProperty("api.host");
    }

    @Override
    public String getAuthenticationServiceUrl() {
        return getHostUrl() + Constantes.AUTENTICAR_SERVICE_URL;
    }

    @Override
    public String getSalvarPautaServiceUrl() {
        return getHostUrl() + Constantes.SALVAR_PAUTA_SERVICE_URL;
    }

    @Override
    public String getAbrirCadastroPautaServiceUrl(Long associado) {
        return getHostUrl() + Constantes.ABRIR_CADASTRO_PAUTA_SERVICE_URL + "?associado="+associado;
    }

    @Override
    public String getListarPautasServiceUrl(Long associado) {
        return getHostUrl() + Constantes.LISTAR_PAUTAS_SERVICE_URL + "?associado="+associado;
    }

    @Override
    public String getVisualizarPautaServiceUrl(Long associado, Long pauta) {
        return getHostUrl() + Constantes.VISUALIZAR_PAUTA_SERVICE_URL+ "?associado="+associado+"&pauta="+pauta;
    }

    @Override
    public String getVotoPositivoUrl() {
        return getHostUrl() + Constantes.VOTAR_POSITIVO_PAUTA_URL;
    }

    @Override
    public String getVotoNegativoUrl() {
        return getHostUrl() + Constantes.VOTAR_NEGATIVO_PAUTA_URL;
    }
}
