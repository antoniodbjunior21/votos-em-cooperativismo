package com.app.cooperativismo.service;

import com.app.cooperativismo.dto.AssociadoDTO;
import com.app.cooperativismo.model.Associado;

import java.util.Optional;

public interface AssociadoService {
    Optional<Associado> findById(Long id);
    void registerNewAccount(AssociadoDTO userDto);
}
