package com.cooperativismo.model;

import javax.persistence.*;

@Entity
@Table(name = "associado", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"}))
public class Associado {

    @Id
    @SequenceGenerator(name = "seq_associado", sequenceName = "seq_associado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_associado")
    private Long id;
    private String cpf;

    public Associado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
