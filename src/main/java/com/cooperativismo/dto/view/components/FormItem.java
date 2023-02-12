package com.cooperativismo.dto.view.components;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormItem  {
    private InputType tipo;

    public FormItem(InputType tipo) {
        this.tipo = tipo;
    }

    public InputType getTipo() {
        return tipo;
    }

    public void setTipo(InputType tipo) {
        this.tipo = tipo;
    }
}
