package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.dto.view.pages.SelectPage;

import java.util.List;

public class ListaPautasPage extends SelectPage {

    public ListaPautasPage(List<SelectItem> opcoes) {
        this.setTitulo("Selecione uma pauta");
        this.setItens(opcoes);
    }
}
