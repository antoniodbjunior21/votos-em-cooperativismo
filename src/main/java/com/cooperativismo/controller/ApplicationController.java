package com.cooperativismo.controller;

import com.cooperativismo.dto.AuthDTO;
import com.cooperativismo.dto.PautaDTO;
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
	public Page index() {
		return this.pageService.getIndexPage();
	}
	@PostMapping(Constantes.AUTENTICAR_SERVICE_URL)
	public Page autenticar(@RequestBody AuthDTO authDTO) {
		return this.pageService.autenticarPor(authDTO.getCpf());
	}
	@PostMapping(Constantes.ABRIR_CADASTRO_PAUTA_SERVICE_URL)
	public Page abrirCadastroPauta(@RequestParam Long associado) {
		return this.pageService.abrirNovoCadastroPauta(associado);
	}
	@PostMapping(Constantes.LISTAR_PAUTAS_SERVICE_URL)
	public Page listarPautas(@RequestParam Long associado) {
		return this.pageService.abrirListaPautas(associado);
	}
	@PostMapping(Constantes.VISUALIZAR_PAUTA_SERVICE_URL)
	public Page visualizarPauta(@RequestParam Long associado, @RequestParam Long pauta) {
		return this.pageService.visualizarPauta(associado, pauta);
	}
	@PostMapping(Constantes.SALVAR_PAUTA_SERVICE_URL)
	public Page salvarPauta(@RequestBody PautaDTO pautaDTO) {
		return this.pageService.salvarPauta(pautaDTO);
	}

	@PostMapping(Constantes.VOTAR_POSITIVO_PAUTA_URL)
	public Page votarPositivo(@RequestBody PautaDTO pautaDTO) {
		return this.pageService.votarPositivo(pautaDTO.getId(), pautaDTO.getAssociado());
	}
	@PostMapping(Constantes.VOTAR_NEGATIVO_PAUTA_URL)
	public Page votarNegativo(@RequestBody PautaDTO pautaDTO) {
		return this.pageService.votarNegativo(pautaDTO.getId(), pautaDTO.getAssociado());
	}
}
