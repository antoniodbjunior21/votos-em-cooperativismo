package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.SelectItem;
import com.cooperativismo.dto.view.pages.SelectPage;

import java.util.ArrayList;

public class MenuPrincipalPage extends SelectPage {

    public MenuPrincipalPage() {
        this.setTitulo("Selecione uma opção");
        this.setItens(new ArrayList<>());
        this.getItens().add(new SelectItem("Cadastrar pauta", null, null));
        this.getItens().add(new SelectItem("Ver pautas abertas", null, null));
    }
}
