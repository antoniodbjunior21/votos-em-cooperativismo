package com.cooperativismo.dto.view.components;

public class CancelButton {
    private String url;
    private String texto;

    public CancelButton(String url, String texto) {
        this.url = url;
        this.texto = texto;
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
}
