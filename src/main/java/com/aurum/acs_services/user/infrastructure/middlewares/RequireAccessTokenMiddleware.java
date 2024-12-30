package com.aurum.acs_services.user.infrastructure.middlewares;

import com.aurum.acs_services.user.application.abstractions.IJwtHandler;
import com.aurum.acs_services.shared.application.exceptions.UnauthorizedException;
import com.aurum.acs_services.shared.infrastructure.decorators.RequireAccessToken;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequireAccessTokenMiddleware {
    private final IJwtHandler jwtHandler;

    public RequireAccessTokenMiddleware(IJwtHandler jwtHandler) {
        this.jwtHandler = jwtHandler;
    }

    @Pointcut("@annotation(requireAccessToken)")
    public void secureMethods(RequireAccessToken requireAccessToken) {}

    @Before("secureMethods(requireAccessToken)")
    public void checkAuthentication(RequireAccessToken requireAccessToken) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replaceAll("Bearer ", "");
        }

        if ((token == null || token.isEmpty()) || !jwtHandler.isValid(token)) {
            throw new UnauthorizedException();
        }
    }
}
