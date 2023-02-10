package com.app.cooperativismo.controller;

import com.app.cooperativismo.dto.AssociadoDTO;
import com.app.cooperativismo.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssociadoController {

	private final AssociadoService service;

	@Autowired
	public AssociadoController(AssociadoService service) {
		this.service = service;
	}

	@GetMapping("/teste")
	public AssociadoDTO teste() {
		AssociadoDTO res = new AssociadoDTO();
		res.nome = "teste";
		return res;
	}

	@GetMapping("/usuario/{id}")
	public AssociadoDTO saveOrUpdate(@PathVariable Long id) {
		AssociadoDTO res = new AssociadoDTO();
		res.nome = "teste";
		return service.findById(id).map(x -> res).orElseGet(() -> res);
	}
}
