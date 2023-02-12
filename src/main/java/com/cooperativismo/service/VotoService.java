package com.cooperativismo.service;

import com.cooperativismo.exceptions.AssociadoJaVotouException;
import com.cooperativismo.exceptions.PautaVencidaException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;

import java.util.Optional;

public interface VotoService {
    Voto salvarPor(Pauta pauta, Associado associado, Boolean positivo) throws AssociadoJaVotouException, PautaVencidaException;
    Optional<Voto> findById(Long id);
    void validarPossibilidadeVotoPor(Associado associado, Pauta pauta) throws AssociadoJaVotouException, PautaVencidaException;
    Integer contarVotosPositivosPor(Pauta pauta);
    Integer contarVotosNegativosPor(Pauta pauta);
    Optional<Voto> findVoteBy(Associado associado, Pauta pauta);
}
