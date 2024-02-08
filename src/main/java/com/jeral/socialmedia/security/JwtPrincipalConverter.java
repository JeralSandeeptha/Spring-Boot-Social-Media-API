package com.jeral.socialmedia.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getId()))
                .username(jwt.getClaim("username").asString())
                .role(jwt.getClaim("role").asString())
                .build();
    }
}
