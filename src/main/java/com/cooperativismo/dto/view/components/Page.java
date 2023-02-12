package com.cooperativismo.dto.view.components;

import com.cooperativismo.dto.view.pages.PageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {
    public PageType tipo;

    public Page(PageType tipo) {
        this.tipo = tipo;
    }

    public PageType getTipo() {
        return tipo;
    }

    public void setTipo(PageType tipo) {
        this.tipo = tipo;
    }
}
