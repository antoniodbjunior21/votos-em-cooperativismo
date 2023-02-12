package com.cooperativismo.dto.view;

import com.cooperativismo.dto.view.components.*;
import com.cooperativismo.dto.view.pages.FormPage;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroPautaPage extends FormPage {

    public CadastroPautaPage(String confirmarUrl, String voltarUrl, Long associado) {
        this.setTitulo("Nova Pauta");
        this.setItens(new ArrayList<>());

        this.getItens().add(new SimpleText("Insira os dados e envie a pauta para votação"));
        this.getItens().add(new InputNumber("duracao", "Duração em minutos (Padrão é 1 min)", null));
        this.getItens().add(new InputText("descricao", "Descreva a questão a ser votada.", null));

        HashMap<String, Object> params = new HashMap<>();
        params.put("associado", associado);

        this.setBotaoCancelar(new CancelButton(voltarUrl, "Voltar", params));
        this.setBotaoOk(new ConfirmButton(confirmarUrl, "Enviar", params));
    }
}
