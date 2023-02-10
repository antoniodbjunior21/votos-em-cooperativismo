package com.app.cooperativismo.service;

import com.app.cooperativismo.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AssociadoRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AssociadoRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
