package com.cooperativismo.service;

import com.cooperativismo.dto.PautaDTO;
import com.cooperativismo.dto.view.*;
import com.cooperativismo.dto.view.components.Page;
import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.exceptions.PautaVencidaException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageServiceImpl implements PageService {
    private final ApiRouteService apiRouteService;
    private final AssociadoService associadoService;
    private final PautaService pautaService;
    private final VotoService votoService;

    @Autowired
    public PageServiceImpl(
            VotoService votoService,
            PautaService pautaService,
            AssociadoService associadoService,
            ApiRouteService apiRouteService) {
        this.apiRouteService = apiRouteService;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
        this.votoService = votoService;
    }
    @Override
    public Page getIndexPage() {
        return getLoginPage("Identifique-se", "Bem vindo a área de associados. Entre com seu CPF para acessar a área de pautas e votações.");
    }

    private LoginPage getLoginPage(String titulo, String descricao){
        String autenticarUrl = apiRouteService.getAuthenticationServiceUrl();
        return new LoginPage(autenticarUrl, titulo, descricao);
    }

    @Override
    public Page autenticarPor(String cpf) {
        try {
            Associado associado = this.associadoService.autenticarOuCriarNovoAssociado(cpf);
            return getMenuPrincipalPage(associado);
        }catch (Exception e){
            if (e instanceof CPFInvalidoException){
                return getLoginPage("CPF Inválido", "Digite um CPF válido para acessar a área de pautas e votações.");
            }
            return getLoginPage("Oops", "Ocorreu um erro desconhecido, o sistema pode estar indisponível temporariamente ou em manutenção.Tente novamente mais tarde.");
        }
    }

    @Override
    public Page abrirMenuPrincipalPor(Long associadoId) {
        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        return getMenuPrincipalPage(associado.get());
    }

    private MenuPrincipalPage getMenuPrincipalPage(Associado associado){
        String cadastroPautaUrl = this.apiRouteService.getAbrirCadastroPautaServiceUrl();
        String listarPautasUrl = this.apiRouteService.getListarPautasServiceUrl();
        return new MenuPrincipalPage(associado, cadastroPautaUrl, listarPautasUrl);
    }

    @Override
    public Page abrirNovoCadastroPauta(Long associadoId) {

        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        String salvarUrl = apiRouteService.getSalvarPautaServiceUrl();
        String voltarUrl = apiRouteService.getMenuPrincipalUrl();

        return new CadastroPautaPage(salvarUrl, voltarUrl, associado.get().getId());
    }

    @Override
    public Page abrirListaPautas(Long associadoId) {
        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        return getListaPautasPage("Selecione uma pauta", associadoId);
    }

    private ListaPautasPage getListaPautasPage(String titulo, Long associadoId){
        List<Pauta> pautas = pautaService.buscarPautasAbertas();

        List<SelectItem> opcoes = new ArrayList<>();

        String url;
        HashMap<String, Object> params;
        for (Pauta pauta : pautas){
            params = new HashMap<>();
            params.put("associado", associadoId);
            params.put("pauta", pauta.getId());
            url = this.apiRouteService.getVisualizarPautaServiceUrl();
            opcoes.add(new SelectItem(pauta.getDescricao(), url, params));
        }

        url = this.apiRouteService.getMenuPrincipalUrl();
        params = new HashMap<>();
        params.put("associado", associadoId);
        opcoes.add(new SelectItem("Voltar", url, params));
        return new ListaPautasPage(titulo, opcoes);
    }

    @Override
    public Page salvarPauta(PautaDTO pautaDTO) {

        Optional<Associado> associado = associadoService.findById(pautaDTO.getAssociado());

        if (!associado.isPresent()){
            return getIndexPage();
        }

        Pauta pauta = new Pauta();
        pauta.setDuracao(pautaDTO.getDuracao());
        if (pauta.getDuracao() == null){
            pauta.setDuracao(1);
        }
        pauta.setDescricao(pautaDTO.getDescricao());
        pauta.setAutor(associado.get());
        pauta.setAbertura(new Date());

        pauta = this.pautaService.save(pauta);
        String votoSimUrl = this.apiRouteService.getVotoPositivoUrl();
        String votoNaoUrl = this.apiRouteService.getVotoNegativoUrl();
        String urlVoltar = this.apiRouteService.getListarPautasServiceUrl();

        return new VotoPendentePage(pauta, associado.get(), votoSimUrl, votoNaoUrl, urlVoltar);
    }

    @Override
    public Page votarPositivo(Long pautaId, Long associadoId){

        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        Optional<Pauta> pauta = pautaService.findById(pautaId);
        if (!pauta.isPresent()){
            return getIndexPage();
        }

       return salvarVotoPor(pauta.get(), associado.get(), true);
    }

    private Page salvarVotoPor(Pauta pauta, Associado associado, Boolean positivo){
        try {
            this.votoService.salvarPor(pauta, associado, Boolean.TRUE.equals(positivo));
        }catch (Exception e){
            if (e instanceof PautaVencidaException){
                return getListaPautasPage("Pauta expirada. Selecione Outra", associado.getId());
            }
            return getListaPautasPage("Selecione uma pauta", associado.getId());
        }

        return visualizarPauta(associado.getId(), pauta.getId());
    }

    @Override
    public Page votarNegativo(Long pautaId, Long associadoId) {
        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        Optional<Pauta> pauta = pautaService.findById(pautaId);
        if (!pauta.isPresent()){
            return getIndexPage();
        }

        return salvarVotoPor(pauta.get(), associado.get(), false);
    }


    @Override
    public Page visualizarPauta(Long associadoId, Long pautaId) {
        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        Optional<Pauta> pauta = pautaService.findById(pautaId);

        if (!pauta.isPresent()){
            return getIndexPage();
        }

        Optional<Voto> votoExistente = votoService.findVoteBy(associado.get(), pauta.get());

        if (votoExistente.isPresent()){
            Integer votosPositivos = this.votoService.contarVotosPositivosPor(pauta.get());
            Integer votosNegativos = this.votoService.contarVotosNegativosPor(pauta.get());

            String urlMenu = this.apiRouteService.getListarPautasServiceUrl();
            String atualizar = this.apiRouteService.getVisualizarPautaServiceUrl();

            HashMap<String, Object> params = new HashMap<>();
            params.put("associado", associadoId);
            params.put("pauta", pautaId);
            return new StatusVotacaoPage(
                    votoExistente.get(),
                    votosPositivos,
                    votosNegativos,
                    urlMenu,
                    atualizar,
                    params
                    );
        }

        try {
            this.votoService.validarPossibilidadeVotoPor(associado.get(), pauta.get());
            String votoSimUrl = this.apiRouteService.getVotoPositivoUrl();
            String votoNaoUrl = this.apiRouteService.getVotoNegativoUrl();
            String urlVoltar = this.apiRouteService.getListarPautasServiceUrl();
            return new VotoPendentePage(pauta.get(), associado.get(), votoSimUrl, votoNaoUrl, urlVoltar);
        }catch (Exception e){
            if (e instanceof PautaVencidaException){
                return getListaPautasPage("Pauta expirada. Selecione outra pauta.",associadoId);
            }
            return getIndexPage();
        }
    }
}
