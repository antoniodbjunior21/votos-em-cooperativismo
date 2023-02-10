package com.app.cooperativismo.controller;

import com.app.cooperativismo.dto.AuthTokenDTO;
import com.app.cooperativismo.dto.AssociadoDTO;
import com.app.cooperativismo.security.AuthenticationFacade;
import com.app.cooperativismo.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AssociadoService service;
	private final AuthenticationFacade authenticationFacade;

	@Autowired
	public AuthController(
			AssociadoService service,
			AuthenticationFacade authenticationFacade) {
		this.authenticationFacade = authenticationFacade;
		this.service = service;
	}

	@GetMapping("/me")
	public AuthTokenDTO me() {
		AssociadoDTO res = new AssociadoDTO();
		res.nome = (String) authenticationFacade.getAuthentication().getPrincipal();

		AuthTokenDTO tokenResource = new AuthTokenDTO();
		tokenResource.accessToken = "$4545";
		tokenResource.userData = res;
		return tokenResource;
	}

	@PostMapping("/register")
	public void register(@RequestBody AssociadoDTO associadoDTO) {
		this.service.registerNewAccount(associadoDTO);
	}
}
