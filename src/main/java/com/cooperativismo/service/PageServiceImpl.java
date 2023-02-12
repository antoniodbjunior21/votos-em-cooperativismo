package com.cooperativismo.service;

import com.cooperativismo.dto.PautaDTO;
import com.cooperativismo.dto.view.*;
import com.cooperativismo.dto.view.components.Page;
import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            Associado associado = this.associadoService.authenticateOrCreateNewAssociado(cpf);
            return getMenuPrincipalPage(associado);
        }catch (Exception e){
            if (e instanceof CPFInvalidoException){
                return getLoginPage("CPF Inválido", "Digite um CPF válido para acessar a área de pautas e votações.");
            }
            return getLoginPage("Oops", "Ocorreu um erro desconhecido, o sistema pode estar indisponível temporariamente ou em manutenção.Tente novamente mais tarde.");
        }
    }

    private MenuPrincipalPage getMenuPrincipalPage(Associado associado){
        String cadastroPautaUrl = this.apiRouteService.getAbrirCadastroPautaServiceUrl(associado.getId());
        String listarPautasUrl = this.apiRouteService.getListarPautasServiceUrl(associado.getId());
        return new MenuPrincipalPage(cadastroPautaUrl, listarPautasUrl);
    }

    @Override
    public Page abrirNovoCadastroPauta(Long associadoId) {

        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        String salvarUrl = apiRouteService.getSalvarPautaServiceUrl();

        return new CadastroPautaPage(salvarUrl, associado.get().getId());
    }

    @Override
    public Page abrirListaPautas(Long associadoId) {
        Optional<Associado> associado = associadoService.findById(associadoId);

        if (!associado.isPresent()){
            return getIndexPage();
        }

        return getListaPautasPage(associadoId);
    }

    private ListaPautasPage getListaPautasPage(Long associadoId){
        List<Pauta> pautas = pautaService.buscarPautasAbertas();

        List<SelectItem> opcoes = new ArrayList<>();

        String url;
        for (Pauta pauta : pautas){
            url = this.apiRouteService.getVisualizarPautaServiceUrl(associadoId, pauta.getId());
            opcoes.add(new SelectItem(pauta.getDescricao(), url));
        }
        return new ListaPautasPage(opcoes);
    }

    @Override
    public Page salvarPauta(PautaDTO pautaDTO) {

        Optional<Associado> associado = associadoService.findById(pautaDTO.getAssociado());

        if (!associado.isPresent()){
            return getIndexPage();
        }

        Pauta pauta = new Pauta();
        pauta.setDuracao(pautaDTO.getDuracao());
        pauta.setDescricao(pautaDTO.getDescricao());
        pauta.setAutor(associado.get());
        pauta.setAbertura(new Date());

        pauta = this.pautaService.save(pauta);
        String votoSimUrl = this.apiRouteService.getVotoPositivoUrl();
        String votoNaoUrl = this.apiRouteService.getVotoNegativoUrl();

        return new VotoPendentePage(pauta, votoSimUrl, votoNaoUrl);
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
            e.printStackTrace();
            return getListaPautasPage(associado.getId());
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

            String urlListarPautas = this.apiRouteService.getListarPautasServiceUrl(associado.get().getId());
            String atualizar = this.apiRouteService.getVisualizarPautaServiceUrl(associado.get().getId(), pauta.get().getId());

            return new StatusVotacaoPage(
                    votoExistente.get(),
                    votosPositivos,
                    votosNegativos,
                    urlListarPautas,
                    atualizar
                    );
        }

        try {
            this.votoService.validarPossibilidadeVotoPor(associado.get(), pauta.get());
            String votoSimUrl = this.apiRouteService.getVotoPositivoUrl();
            String votoNaoUrl = this.apiRouteService.getVotoNegativoUrl();
            return new VotoPendentePage(pauta.get(), votoSimUrl, votoNaoUrl);
        }catch (Exception e){
            return getIndexPage();
        }
    }
}
