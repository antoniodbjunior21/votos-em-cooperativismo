package com.cooperativismo.service;

public interface ApiRouteService {
    String getAuthenticationServiceUrl();
    String getSalvarPautaServiceUrl();
    String getAbrirCadastroPautaServiceUrl(Long associado);
    String getListarPautasServiceUrl(Long associado);
    String getVisualizarPautaServiceUrl(Long associado, Long pauta);
    String getVotoPositivoUrl();
    String getVotoNegativoUrl();
    String getHostUrl();
}
