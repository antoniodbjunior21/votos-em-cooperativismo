package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.CancelButton;
import com.cooperativismo.dto.view.components.ConfirmButton;
import com.cooperativismo.dto.view.components.SimpleText;
import com.cooperativismo.dto.view.pages.FormPage;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusVotacaoPage extends FormPage {

    public StatusVotacaoPage(Voto voto,
                             Integer votosPositivos,
                             Integer votosNegativos,
                             String listaPautasUrl,
                             String atualizarUrl) {
        this.setTitulo("Pauta");
        this.setItens(new ArrayList<>());

        Pauta pauta = voto.getPauta();
        this.getItens().add(new SimpleText(pauta.getDescricao()));

        if (Boolean.TRUE.equals(voto.getPositivo())){
            this.getItens().add(new SimpleText("Você votou SIM para esta pauta."));
        }else {
            this.getItens().add(new SimpleText("Você votou NÃO para esta pauta."));
        }

        this.getItens().add(new SimpleText("Votos a favor: " + votosPositivos));
        this.getItens().add(new SimpleText("Votos contra: " + votosNegativos ));

        HashMap<String, Object> params = new HashMap<>();

        this.setBotaoCancelar(new CancelButton(listaPautasUrl, "Voltar"));
        this.setBotaoOK(new ConfirmButton(atualizarUrl, "Atualizar", params));
    }
}
