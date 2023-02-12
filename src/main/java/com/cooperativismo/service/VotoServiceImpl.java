package com.cooperativismo.service;

import com.cooperativismo.exceptions.AssociadoJaVotouException;
import com.cooperativismo.exceptions.PautaVencidaException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.Voto;
import com.cooperativismo.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;

    @Autowired
    public VotoServiceImpl(
            VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }


    @Override
    public Voto salvarPor(Pauta pauta, Associado associado, Boolean positivo) throws AssociadoJaVotouException, PautaVencidaException {

        validarPossibilidadeVotoPor(associado, pauta);

        Voto voto = new Voto();
        voto.setPositivo(Boolean.TRUE.equals(positivo));
        voto.setAssociado(associado);
        voto.setPauta(pauta);

        return this.votoRepository.save(voto);
    }

    @Override
    public Optional<Voto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void validarPossibilidadeVotoPor(Associado associado, Pauta pauta) throws AssociadoJaVotouException, PautaVencidaException {
        boolean jaVotou = this.votoRepository.countVotoByAssociadoAndPauta(associado, pauta) > 0;

        if (jaVotou){
            throw new AssociadoJaVotouException();
        }

        Integer validade = pauta.getDuracao();
        Calendar expiracao = Calendar.getInstance();
        expiracao.setTime(pauta.getAbertura());
        expiracao.add(Calendar.MINUTE, validade);

        Calendar agora = Calendar.getInstance();

        Date dtAgora = agora.getTime();
        Date dtExpiracao = expiracao.getTime();

        if (dtExpiracao.before(dtAgora)){
            throw new PautaVencidaException();
        }
    }

    @Override
    public Integer contarVotosPositivosPor(Pauta pauta) {
        return this.votoRepository.countVotoByPautaAndPositivoIsTrue(pauta);
    }

    @Override
    public Integer contarVotosNegativosPor(Pauta pauta) {
        return this.votoRepository.countVotoByPautaAndPositivoIsFalse(pauta);
    }

    @Override
    public Optional<Voto> findVoteBy(Associado associado, Pauta pauta) {
        return this.votoRepository.findVotoByAssociadoAndPauta(associado, pauta);
    }

}
