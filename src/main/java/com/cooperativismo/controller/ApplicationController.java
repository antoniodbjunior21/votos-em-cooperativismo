package com.cooperativismo.controller;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.dto.view.LoginPage;
import com.cooperativismo.dto.view.components.BaseViewComponent;
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
	public BaseViewComponent autenticar(@RequestBody LoginDTO login) {
		this.service.authenticate(login);
		return null;
	}
}
