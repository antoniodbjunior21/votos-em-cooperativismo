package com.cooperativismo.model;

import javax.persistence.*;

@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @SequenceGenerator(name = "seq_pauta", sequenceName = "seq_pauta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pauta")
    private Long id;
    private String descricao;

    public Pauta() {
    }

    public Pauta(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
