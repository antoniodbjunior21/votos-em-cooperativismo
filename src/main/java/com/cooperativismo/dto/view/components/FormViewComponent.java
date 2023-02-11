package com.cooperativismo.dto.view.components;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormViewComponent extends BaseViewComponent {

    private String titulo;
    private List<FormItemViewComponent> itens = new ArrayList<>();
    private ConfirmButtontViewComponent botaoOK;
    private CancelButtontViewComponent botaoCancelar;

    public FormViewComponent() {
        super(ComponentTypeViewDTO.FORMULARIO);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<FormItemViewComponent> getItens() {
        return itens;
    }

    public void setItens(List<FormItemViewComponent> itens) {
        this.itens = itens;
    }

    public ConfirmButtontViewComponent getBotaoOK() {
        return botaoOK;
    }

    public void setBotaoOK(ConfirmButtontViewComponent botaoOK) {
        this.botaoOK = botaoOK;
    }

    public CancelButtontViewComponent getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(CancelButtontViewComponent botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }
}
