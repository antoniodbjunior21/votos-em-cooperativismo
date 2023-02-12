package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.dto.view.pages.SelectPage;
import com.cooperativismo.model.Associado;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuPrincipalPage extends SelectPage {

    public MenuPrincipalPage(Associado associado, String urlCadastroPauta, String urlLisPautasAbertas) {
        this.setTitulo("Selecione uma opção");
        this.setItens(new ArrayList<>());

        HashMap<String, Object> params = new HashMap<>();
        params.put("associado", associado.getId());
        this.getItens().add(new SelectItem("Cadastrar pauta", urlCadastroPauta, params));
        this.getItens().add(new SelectItem("Ver pautas abertas", urlLisPautasAbertas, params));
    }
}
