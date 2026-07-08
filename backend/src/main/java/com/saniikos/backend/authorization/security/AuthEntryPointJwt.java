package com.saniikos.backend.authorization.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        log.warn("Unauthorized request: {}", authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String errorMessage = authException.getMessage() != null
                ? authException.getMessage()
                : "Unauthorized";

        response.getWriter().write(String.format(
                "{\"error\":\"%s\",\"status\":%d}",
                errorMessage,
                HttpStatus.UNAUTHORIZED.value()));
    }
}