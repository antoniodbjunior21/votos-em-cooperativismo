package com.cooperativismo.service;

import com.cooperativismo.exceptions.AssociadoJaVotouException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;

import java.util.Optional;

public interface VotoService {
    Voto salvarPor(Pauta pauta, Associado associado, Boolean positivo) throws AssociadoJaVotouException;
    Optional<Voto> findById(Long id);
    void validarPossibilidadeVotoPor(Associado associado, Pauta pauta) throws AssociadoJaVotouException;
    Integer contarVotosPositivosPor(Pauta pauta);
    Integer contarVotosNegativosPor(Pauta pauta);
    Optional<Voto> findVoteBy(Associado associado, Pauta pauta);
}
