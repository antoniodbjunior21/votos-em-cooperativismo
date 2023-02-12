package com.cooperativismo.service;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import com.cooperativismo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Optional<Associado> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Associado authenticate(LoginDTO login) throws CPFInvalidoException {
        String cpf = login.getCpf();

        if (Utils.isNullOrEmpty(cpf)){
            throw new CPFInvalidoException();
        }

        Optional<Associado> associado = this.associadoRepository.findByCpf(cpf);

        if (associado.isPresent()){
            return associado.get();
        }

        Associado novoAssociado = new Associado();
        novoAssociado.setCpf(cpf);
        novoAssociado = this.associadoRepository.save(novoAssociado);
        return novoAssociado;
    }

}
