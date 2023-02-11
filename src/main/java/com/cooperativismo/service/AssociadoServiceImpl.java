package com.cooperativismo.service;

import com.cooperativismo.dto.AssociadoDTO;
import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.exceptions.UserAlreadyExistException;
import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository usuarioRepository;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Associado> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void authenticate(LoginDTO login) throws UserAlreadyExistException {
        if (emailExists(login.getCpf())) {
            throw new UserAlreadyExistException(login.getCpf());
        }
        Associado user = new Associado();
        BeanUtils.copyProperties(login, user);
        usuarioRepository.save(user);
    }

    private boolean emailExists(String email) {
        return usuarioRepository.countByCpf(email) > 0;
    }
}
