package com.cooperativismo.service;

import com.cooperativismo.exceptions.CPFInvalidoException;

public interface AssociadoService {
    void authenticateOrCreateNewAssociado(String cpf) throws CPFInvalidoException;
}
