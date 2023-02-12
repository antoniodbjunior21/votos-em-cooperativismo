package com.cooperativismo.service;

import com.cooperativismo.model.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaService {
    Pauta save(Pauta pauta);
    Optional<Pauta> findById(Long id);
    List<Pauta> buscarPautasAbertas();
}
