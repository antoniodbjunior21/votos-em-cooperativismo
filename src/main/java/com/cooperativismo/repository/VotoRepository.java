package com.cooperativismo.repository;


import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long>{
    int countVotoByAssociadoAndPauta(Associado associado, Pauta pauta);
    Integer countVotoByPautaAndPositivoIsTrue(Pauta pauta);
    Integer countVotoByPautaAndPositivoIsFalse(Pauta pauta);
    Optional<Voto> findVotoByAssociadoAndPauta(Associado associado, Pauta pauta);
}
