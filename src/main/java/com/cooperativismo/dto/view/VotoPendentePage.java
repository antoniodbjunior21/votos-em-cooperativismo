package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.dto.view.pages.SelectPage;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;

import java.util.ArrayList;
import java.util.HashMap;

public class VotoPendentePage extends SelectPage {

    public VotoPendentePage(Pauta pauta, Associado associado, String urlVotoSim, String urlVotoNao, String urlVoltar) {

        this.setTitulo(pauta.getDescricao());
        this.setItens(new ArrayList<>());

        HashMap<String, Object> params = new HashMap<>();

        params.put("id", pauta.getId());
        params.put("associado", associado.getId());

        this.getItens().add(new SelectItem("Sim", urlVotoSim, params));
        this.getItens().add(new SelectItem("Não", urlVotoNao, params));
        this.getItens().add(new SelectItem("Voltar", urlVoltar, params));
    }
}
