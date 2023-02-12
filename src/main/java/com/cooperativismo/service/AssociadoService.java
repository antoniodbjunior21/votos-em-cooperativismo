package com.cooperativismo.service;

import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;

import java.util.Optional;

public interface AssociadoService {
    Associado authenticateOrCreateNewAssociado(String cpf) throws CPFInvalidoException;
    Optional<Associado> findByCpf(String cpf);
    Optional<Associado> findById(Long id);
}
