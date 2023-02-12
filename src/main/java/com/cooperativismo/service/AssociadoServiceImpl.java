package com.cooperativismo.service;

import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import com.cooperativismo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final RestTemplate restTemplate;
    private final Environment environment;

    @Autowired
    public AssociadoServiceImpl(
            Environment environment,
            RestTemplate restTemplate,
            AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public Associado autenticarOuCriarNovoAssociado(String cpf) throws CPFInvalidoException{

        if (Utils.isNullOrEmpty(cpf)) {
            throw new CPFInvalidoException();
        }

        //A validação remota parace não estar funcionando, descomentar, caso queira checar
        /*
        Boolean valido = validarRemotamente(cpf);

        if (!valido){
            throw new CPFInvalidoException();
        }*/

        cpf = cpf.replaceAll("[^0-9]", "");

        if (!CPFValidator.isCPF(cpf)){
            throw new CPFInvalidoException();
        }

        Optional<Associado> associado = this.associadoRepository.findByCpf(cpf);

        if (associado.isPresent()) {
            return associado.get();
        }

        Associado novoAssociado = new Associado();
        novoAssociado.setCpf(cpf);
        return this.associadoRepository.save(novoAssociado);
    }

    @Override
    public Optional<Associado> findByCpf(String cpf) {
        return this.associadoRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Associado> findById(Long id) {
        return this.associadoRepository.findById(id);
    }

    private Boolean validarRemotamente(String cpf) throws CPFInvalidoException {
        try {
            String url = environment.getProperty("cpf.validador.remoto") + cpf;
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            return response.getBody().equals("ABLE_TO_VOTE");
        } catch (HttpStatusCodeException ex) {
            throw new CPFInvalidoException();
        }
    }

}
