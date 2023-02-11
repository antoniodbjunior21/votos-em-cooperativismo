package com.cooperativismo.dto.view.components;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberInputViewComponent extends FormItemViewComponent{

    private String id;
    private String titulo;
    private Integer valor;

    public NumberInputViewComponent(String id, String titulo, Integer valor) {
        super(ComponentTypeViewDTO.INPUT_NUMERO);
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
