package com.cooperativismo.dto.view.pages;

import com.cooperativismo.dto.view.components.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormPage extends ViewComponent {

    private String titulo;
    private List<FormItem> itens = new ArrayList<>();
    private ConfirmButton botaoOK;
    private CancelButton botaoCancelar;

    public FormPage() {
        super(PageType.FORMULARIO);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<FormItem> getItens() {
        return itens;
    }

    public void setItens(List<FormItem> itens) {
        this.itens = itens;
    }

    public ConfirmButton getBotaoOK() {
        return botaoOK;
    }

    public void setBotaoOK(ConfirmButton botaoOK) {
        this.botaoOK = botaoOK;
    }

    public CancelButton getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(CancelButton botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }
}
