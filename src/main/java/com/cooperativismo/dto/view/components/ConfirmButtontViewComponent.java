package com.cooperativismo.dto.view.components;

import java.util.HashMap;

public class ConfirmButtontViewComponent{
    private String url;
    private String texto;
    private HashMap<String, Object> body = new HashMap<>();

    public ConfirmButtontViewComponent(String url, String texto, HashMap<String, Object> body) {
        this.url = url;
        this.texto = texto;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }
}
