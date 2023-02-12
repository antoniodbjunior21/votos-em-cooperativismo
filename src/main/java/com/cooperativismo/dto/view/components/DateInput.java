package com.cooperativismo.dto.view.components;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateInput extends FormItem {

    public String id;
    public String titulo;
    public String valor;

    public DateInput() {
        super(InputType.INPUT_DATA);
    }

}
