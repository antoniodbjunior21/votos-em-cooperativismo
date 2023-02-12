package com.cooperativismo.controller;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.dto.view.LoginPage;
import com.cooperativismo.dto.view.MenuPrincipalPage;
import com.cooperativismo.dto.view.components.ViewComponent;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(Constants.API_V1_URL)
public class ApplicationController {

	private final AssociadoService service;

	@Autowired
	public ApplicationController(AssociadoService service) {
		this.service = service;
	}

	@GetMapping("/")
	public LoginPage index(HttpServletRequest request) {
		String autenticarUrl = Constants.getAuthenticateURL(request);
		String titulo = "Identifique-se";
		String descricao = "Bem vindo a área de associados. Entre com seu CPF para acessar a área de pautas e votações.";
		return new LoginPage(autenticarUrl, titulo, descricao );
	}

	@PostMapping(Constants.AUTHENTICATE_URL)
	public ViewComponent autenticar(@RequestBody LoginDTO login, HttpServletRequest request) {
		String autenticarUrl = Constants.getAuthenticateURL(request);
		String titulo;
		String descricao;
		try {
			this.service.authenticate(login);
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
