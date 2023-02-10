package com.app.cooperativismo.security;

import com.app.cooperativismo.dto.AuthTokenDTO;
import com.app.cooperativismo.dto.AssociadoDTO;
import com.app.cooperativismo.service.AppAuthenticationManager;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.app.cooperativismo.security.SecurityConstants.EXPIRATION_TIME;
import static com.app.cooperativismo.security.SecurityConstants.SECRET;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    public JWTAuthenticationFilter(AppAuthenticationManager authProviderService) {
        super(authProviderService);
        setFilterProcessesUrl("/api/v1/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        String username = req.getParameter("email");
        String password = req.getParameter("senha");

        User user = new User(username, password, new ArrayList<>());
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        User user = (User) auth.getPrincipal();
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        AssociadoDTO usuarioResource = new AssociadoDTO();
        usuarioResource.nome = user.getUsername();

        AuthTokenDTO tokenResource = new AuthTokenDTO();
        tokenResource.accessToken = "Bearer "+token;
        tokenResource.userData = usuarioResource;

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tokenResource);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print(jsonInString);
        res.getWriter().flush();
    }
}
