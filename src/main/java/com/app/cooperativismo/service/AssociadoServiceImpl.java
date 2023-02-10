package com.app.cooperativismo.service;

import com.app.cooperativismo.dto.AssociadoDTO;
import com.app.cooperativismo.exceptions.UserAlreadyExistException;
import com.app.cooperativismo.model.Associado;
import com.app.cooperativismo.repository.AssociadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociadoServiceImpl.class);

    private final AssociadoRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Associado> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void registerNewAccount(AssociadoDTO userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getCpf())) {
            throw new UserAlreadyExistException(userDto.getCpf());
        }
        Associado user = new Associado();
        BeanUtils.copyProperties(userDto, user);
        //user.setSenha(passwordEncoder.encode(user.getSenha()));
        usuarioRepository.save(user);
    }

    private boolean emailExists(String email) {
        return usuarioRepository.countByCpf(email) > 0;
    }
}
