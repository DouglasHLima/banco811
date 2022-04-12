package com.santander.banco811.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.banco811.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    public static final int EXPIRATION_TOKEN = 600_000;
    public static final String PASSWORD_TOKEN = "5db0e823-e5d8-45bf-8219-745a89913276";
    private final AuthenticationManager authenticationManager;

    @Override
    @Bean
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            var user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(),
                    user.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Failed to authenticate user ", e);
        }
    }

    @Override
    @Bean
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var userData = (UserDetailsData) authResult.getPrincipal();
        var token = JWT.create()
                .withSubject(userData.getUsername())
                .withExpiresAt(Date.from(Instant.now().plus(EXPIRATION_TOKEN, ChronoUnit.MILLIS)))
                .sign(Algorithm.HMAC512(PASSWORD_TOKEN));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
