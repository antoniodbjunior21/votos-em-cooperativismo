package com.cooperativismo.service;

import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class AppAuthenticationManager implements AuthenticationManager {

    private final AssociadoRepository associadoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppAuthenticationManager(AssociadoRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.associadoRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        User user = (User) auth.getPrincipal();
        String login = user.getUsername();
        String cpf = user.getPassword();

        Optional<Associado> usuario = associadoRepository.findByCpf(login);

        if (!usuario.isPresent()){
            throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
        }

        Associado usuarioBd = usuario.get();

        if (usuarioBd.getCpf().equals(cpf)) {
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(user, cpf, authorities);
        }

        throw new BadCredentialsException("Login e/ou Senha inválidos.");
    }

}