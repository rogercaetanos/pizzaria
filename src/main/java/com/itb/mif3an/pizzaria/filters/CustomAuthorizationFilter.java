package com.itb.mif3an.pizzaria.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    List<String> blackList = new ArrayList<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/v1/login")) {
            filterChain.doFilter(request, response);
        } else if (request.getServletPath().equals("/api/v1/logout")) {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
               // String stringTokens = authorizationHeader.substring("Bearer ".length());
                // String[] arrayTokens = stringTokens.split(" Refresh ");
              //  String access_token = arrayTokens[0];
                //String refresh_token = arrayTokens[1];
                String access_token = authorizationHeader.substring("Bearer ".length());
                System.out.println(access_token);
               // System.out.println(refresh_token);
                removeTokenInvalidTheBlackList(); // removendo tokens antigos (inválidos)
                blackList.add(access_token);
               // blackList.add(refresh_token);
                System.out.println(blackList.size());
                filterChain.doFilter(request, response);
            }
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    // Nota: Aqui verificar na black-list para verificar se o token ainda é válido
                    token = verifyInBlackList(token);
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    String idUser = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(idUser, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);

                } catch (Exception exception) {
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);

                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private void removeTokenInvalidTheBlackList() {


        for (int i = 0; i < blackList.size(); i++) {
            try {

                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(blackList.get(i)); // retorna uma exception, coso inválido
            } catch (Exception e) {
                blackList.remove(blackList.get(i));
            }
        }


    }

    private String verifyInBlackList(String token) throws Exception {
        for (int i = 0; i < blackList.size(); i++) {
            if (blackList.get(i).equals(token)) {
                throw new Exception("Acesso negado!");
            }
        }
        return token;
    }
}



