package com.cooperativismo.controller;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.dto.view.LoginPage;
import com.cooperativismo.dto.view.components.Page;
import com.cooperativismo.service.PageService;
import com.cooperativismo.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.API_V1_URL)
public class ApplicationController {

	private final PageService pageService;


	@Autowired
	public ApplicationController(PageService pageService) {
		this.pageService = pageService;
	}

	@GetMapping("/")
	public LoginPage index() {
		return this.pageService.getLoginPage();
	}

	@PostMapping(Constantes.AUTHENTICATE_URL)
	public Page autenticar(@RequestBody LoginDTO login) {
		return this.pageService.authenticateAndGetPageResponse(login);
	}
}
