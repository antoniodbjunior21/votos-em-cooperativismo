package com.cooperativismo.dto.view.components;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextViewComponent extends FormItemViewComponent{
    private String texto;

    public TextViewComponent(String texto) {
        super(ComponentTypeViewDTO.TEXTO);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
