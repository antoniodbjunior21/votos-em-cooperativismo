package com.cooperativismo.service;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.model.Associado;

import java.util.Optional;

public interface AssociadoService {
    Optional<Associado> findById(Long id);
    void authenticate(LoginDTO loginDTO);
}
