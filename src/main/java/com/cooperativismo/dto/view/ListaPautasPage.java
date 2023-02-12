package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.dto.view.pages.SelectPage;

import java.util.List;

public class ListaPautasPage extends SelectPage {

    public ListaPautasPage(String titulo, List<SelectItem> opcoes) {
        this.setTitulo(titulo);
        this.setItens(opcoes);
    }
}
