package com.aurum.acs_services.user.infrastructure.handlers;

import com.aurum.acs_services.user.application.abstractions.IJwtHandler;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.aurum.acs_services.user.domain.validators.CpfValidator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public Boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = verifier.verify(token);

            String name = decodedJWT.getClaim("name").asString();
            String email = decodedJWT.getClaim("email").asString();
            String cpf = decodedJWT.getClaim("cpf").asString();

            if (name == null || email == null || cpf == null) {
                return false;
            }

            if (name.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
                return false;
            }

            if (!CpfValidator.isValid(cpf)) {
                return false;
            }

            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
