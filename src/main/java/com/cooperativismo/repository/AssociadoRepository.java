package com.cooperativismo.repository;


import com.cooperativismo.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{
    Optional<Associado> findByCpf(String cpf);
    long countByCpf(String cpf);
}
