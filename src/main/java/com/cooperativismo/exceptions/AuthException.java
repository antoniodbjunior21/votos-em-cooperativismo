package com.cooperativismo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException() {
        super("Impossivel Auth");
    }
}
