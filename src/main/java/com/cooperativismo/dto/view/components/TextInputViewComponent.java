package com.cooperativismo.dto.view.components;

public class TextInputViewComponent extends FormItemViewComponent{

    public String id;
    public String titulo;
    public String valor;

    public TextInputViewComponent() {
        super(ComponentTypeViewDTO.INPUT_TEXTO);
    }

}
