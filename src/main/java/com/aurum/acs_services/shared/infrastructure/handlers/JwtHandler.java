package com.aurum.acs_services.shared.infrastructure.handlers;

import com.aurum.acs_services.shared.application.abstractions.IJwtHandler;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtHandler  implements IJwtHandler {
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Override
    public String encode(UserAggregate user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withClaim("cpf", user.getCpf())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(algorithm);
    }
}
