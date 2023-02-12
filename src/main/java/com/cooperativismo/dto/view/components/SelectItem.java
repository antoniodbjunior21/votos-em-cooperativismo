package com.cooperativismo.dto.view.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectItem {
    private String texto;
    private String url;
    private HashMap<String, Object> body;

    public SelectItem(String texto, String url, HashMap<String, Object> body) {
        this.texto = texto;
        this.url = url;
        this.body = body;
    }

    public SelectItem(String texto, String url) {
        this.texto = texto;
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }
}
