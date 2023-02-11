package com.cooperativismo.dto.view.components;

public class InputTextViewComponent extends FormItemViewComponent{
    public String texto = "";
    public InputTextViewComponent() {
        super(ComponentTypeViewDTO.TEXTO);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
