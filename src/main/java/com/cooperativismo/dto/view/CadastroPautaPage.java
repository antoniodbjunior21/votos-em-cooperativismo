package com.cooperativismo.dto.view;

import com.cooperativismo.dto.PautaDTO;
import com.cooperativismo.dto.view.components.ConfirmButton;
import com.cooperativismo.dto.view.components.InputNumber;
import com.cooperativismo.dto.view.components.InputText;
import com.cooperativismo.dto.view.components.SimpleText;
import com.cooperativismo.dto.view.pages.FormPage;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroPautaPage extends FormPage {

    public CadastroPautaPage(String url, Long associado) {
        this.setTitulo("Nova Pauta");
        this.setItens(new ArrayList<>());

        this.getItens().add(new SimpleText("Insira os dados e envie a pauta para votação"));
        this.getItens().add(new InputNumber("duracao", "Duração em minutos (Padrão é 1 min)", null));
        this.getItens().add(new InputText("descricao", "Descreva a questão a ser votada.", null));

        HashMap<String, Object> params = new HashMap<>();
        params.put("associado", associado);
        this.setBotaoOK(new ConfirmButton(url, "Enviar", params));
    }
}
