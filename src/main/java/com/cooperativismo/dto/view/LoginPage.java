package com.cooperativismo.dto.view;
import com.cooperativismo.dto.view.components.ConfirmButtontViewComponent;
import com.cooperativismo.dto.view.components.FormViewComponent;
import com.cooperativismo.dto.view.components.NumberInputViewComponent;
import com.cooperativismo.dto.view.components.TextViewComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginPage extends FormViewComponent {

    public LoginPage(String url, String titulo, String descricao) {

        this.setTitulo(titulo);

        TextViewComponent text = new TextViewComponent(descricao);

        NumberInputViewComponent numberInput = new NumberInputViewComponent(
                "cpf",
                "Digite seu CPF",
                null
        );

        this.setItens(new ArrayList<>());
        this.getItens().add(text);
        this.getItens().add(numberInput);

        ConfirmButtontViewComponent buttonOk = new ConfirmButtontViewComponent(url, "Acessar", new HashMap<>());

        this.setBotaoOK(buttonOk);
    }
}
