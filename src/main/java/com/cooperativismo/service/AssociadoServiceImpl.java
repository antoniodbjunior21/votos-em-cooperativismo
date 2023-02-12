package com.cooperativismo.service;

import com.cooperativismo.exceptions.BusinessException;
import com.cooperativismo.exceptions.CPFInvalidoException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import com.cooperativismo.utils.Utils;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final ApiRouteService apiRouteService;

    @Autowired
    public AssociadoServiceImpl(
            ApiRouteService apiRouteService,
            Environment environment,
            RestTemplate restTemplate,
            AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
        this.restTemplate = restTemplate;
        this.apiRouteService = apiRouteService;
        this.environment = environment;
    }

    @Override
    public void authenticateOrCreateNewAssociado(String cpf) throws CPFInvalidoException{

        if (Utils.isNullOrEmpty(cpf)) {
            throw new CPFInvalidoException();
        }

        Optional<Associado> associado = this.associadoRepository.findByCpf(cpf);

        if (associado.isPresent()) {
            return;
        }

        Associado novoAssociado = new Associado();
        novoAssociado.setCpf(cpf);
        this.associadoRepository.save(novoAssociado);
    }

    public boolean isAbleToVote(String cpf) throws BusinessException {
        try {
            String url = environment.getProperty("cpf.service.url") + cpf;
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            return response.getBody().equals(ABLE_TO_VOTE);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode()== HttpStatus.NOT_FOUND)
                throw new BusinessException("Invalid CPF!");

            throw new IntegrationException("An error occurred while trying to access 'CpfService'.");
        }
    }

}
