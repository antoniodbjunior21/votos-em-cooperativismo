package com.cooperativismo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @SequenceGenerator(name = "seq_pauta", sequenceName = "seq_pauta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pauta")
    private Long id;
    private String descricao;
    private Integer duracao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date abertura;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Associado autor;

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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Associado getAutor() {
        return autor;
    }

    public void setAutor(Associado autor) {
        this.autor = autor;
    }
}
