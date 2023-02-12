package com.cooperativismo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDataDTO {
    public Long associado;
    public Long pauta;
    public PostDataDTO() {
    }

    public Long getAssociado() {
        return associado;
    }

    public void setAssociado(Long associado) {
        this.associado = associado;
    }

    public Long getPauta() {
        return pauta;
    }

    public void setPauta(Long pauta) {
        this.pauta = pauta;
    }
}
