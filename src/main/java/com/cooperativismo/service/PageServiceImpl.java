package com.cooperativismo.service;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.dto.view.LoginPage;
import com.cooperativismo.dto.view.MenuPrincipalPage;
import com.cooperativismo.dto.view.components.Page;
import com.cooperativismo.exceptions.CPFInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {
    private final ApiRouteService apiRouteService;
    private final AssociadoService associadoService;

    @Autowired
    public PageServiceImpl(
            AssociadoService associadoService,
            ApiRouteService apiRouteService) {
        this.apiRouteService = apiRouteService;
        this.associadoService = associadoService;
    }
    @Override
    public LoginPage getLoginPage() {
        String autenticarUrl = apiRouteService.getAuthenticationServiceUrl();
        String titulo = "Identifique-se";
        String descricao = "Bem vindo a área de associados. Entre com seu CPF para acessar a área de pautas e votações.";
        return new LoginPage(autenticarUrl, titulo, descricao );
    }

    @Override
    public Page authenticateAndGetPageResponse(LoginDTO loginDTO) {
        String autenticarUrl = apiRouteService.getAuthenticationServiceUrl();
        String titulo;
        String descricao;
        try {
            this.associadoService.authenticateOrCreateNewAssociado(loginDTO.getCpf());
            return new MenuPrincipalPage();
        }catch (Exception e){
            if (e instanceof CPFInvalidoException){
                titulo = "CPF Inválido";
                descricao = "Digite um CPF válido para acessar a área de pautas e votações.";
                return new LoginPage(autenticarUrl, titulo, descricao );
            }
            titulo = "Oops";
            descricao = "Ocorreu um erro desconhecido, o sistema pode estar indisponível temporariamente ou em manutenção." +
                    "Tente novamente mais tarde.";
            return new LoginPage(autenticarUrl, titulo, descricao);
        }
    }


}
