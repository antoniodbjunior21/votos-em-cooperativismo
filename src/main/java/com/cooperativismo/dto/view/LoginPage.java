package com.cooperativismo.dto.view;
import com.cooperativismo.dto.view.components.ConfirmButton;
import com.cooperativismo.dto.view.components.SimpleText;
import com.cooperativismo.dto.view.pages.FormPage;
import com.cooperativismo.dto.view.components.InputNumber;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginPage extends FormPage {

    public LoginPage(String url, String titulo, String descricao) {
        this.setTitulo(titulo);
        this.setItens(new ArrayList<>());

        this.getItens().add(new SimpleText(descricao));
        this.getItens().add(new InputNumber("cpf", "Digite seu CPF", null));

        this.setBotaoOK(new ConfirmButton(url, "Acessar", new HashMap<>()));
    }
}
