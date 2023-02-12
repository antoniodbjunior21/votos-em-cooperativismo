package com.cooperativismo.service;

import com.cooperativismo.dto.PautaDTO;
import com.cooperativismo.dto.view.components.Page;

public interface PageService {
    Page getIndexPage();
    Page autenticarPor(String cpf);
    Page abrirNovoCadastroPauta(Long associado);
    Page abrirListaPautas(Long associado);
    Page salvarPauta(PautaDTO pautaDTO);
    Page votarPositivo(Long pautaId, Long associadoId);
    Page votarNegativo(Long pautaId, Long associadoId);
    Page visualizarPauta(Long associado, Long pauta);
}
