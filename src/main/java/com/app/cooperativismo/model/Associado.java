package com.app.cooperativismo.model;
import com.app.cooperativismo.dto.AssociadoDTO;

import javax.persistence.*;

@Entity
@Table(name = "associado", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"}))
public class Associado {

    @Id
    @SequenceGenerator(name = "seq_associado", sequenceName = "seq_associado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_associado")
    private Long id;
    private String nome;
    private String cpf;

    public Associado() {
    }

    public Associado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Associado fromResource(AssociadoDTO resource){
        return new Associado(
                resource.id,
                resource.nome
                );
    }
    public AssociadoDTO toResource(){
        return new AssociadoDTO(
                this.id,
                this.nome);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
