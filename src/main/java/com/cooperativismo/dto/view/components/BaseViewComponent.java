package com.cooperativismo.dto.view.components;

public class BaseViewComponent {
    public ComponentTypeViewDTO tipo;

    public BaseViewComponent(ComponentTypeViewDTO tipo) {
        this.tipo = tipo;
    }

    public ComponentTypeViewDTO getTipo() {
        return tipo;
    }

    public void setTipo(ComponentTypeViewDTO tipo) {
        this.tipo = tipo;
    }
}
