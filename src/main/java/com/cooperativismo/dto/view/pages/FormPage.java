package com.cooperativismo.dto.view.pages;

import com.cooperativismo.dto.view.components.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormPage extends Page {

    private String titulo;
    private List<FormItem> itens = new ArrayList<>();
    private ConfirmButton botaoOk;
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

    public ConfirmButton getBotaoOk() {
        return botaoOk;
    }

    public void setBotaoOk(ConfirmButton botaoOk) {
        this.botaoOk = botaoOk;
    }

    public CancelButton getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(CancelButton botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }
}
