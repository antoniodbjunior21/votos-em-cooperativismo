package com.cooperativismo.service;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(
            PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta save(Pauta pauta) {
        return this.pautaRepository.save(pauta);
    }

    @Override
    public Optional<Pauta> findById(Long id) {
        return this.pautaRepository.findById(id);
    }

    @Override
    public List<Pauta> buscarPautasAbertas() {
        return this.pautaRepository.findAll();
    }
}
