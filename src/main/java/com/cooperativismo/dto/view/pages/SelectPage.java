package com.cooperativismo.dto.view.pages;

import com.cooperativismo.dto.view.components.Page;
import com.cooperativismo.dto.view.components.SelectItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectPage extends Page {

    private String titulo;
    private List<SelectItem> itens = new ArrayList<>();

    public SelectPage() {
        super(PageType.SELECAO);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<SelectItem> getItens() {
        return itens;
    }

    public void setItens(List<SelectItem> itens) {
        this.itens = itens;
    }
}
