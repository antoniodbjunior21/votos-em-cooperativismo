package com.cooperativismo.service;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;

import java.util.Optional;

public interface AssociadoService {
    Optional<Associado> findById(Long id);
    Associado authenticate(LoginDTO loginDTO) throws CPFInvalidoException;
}
