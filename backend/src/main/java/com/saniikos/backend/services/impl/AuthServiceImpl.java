package com.saniikos.backend.services.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saniikos.backend.entities.User;
import com.saniikos.backend.repositories.UserRepository;
import com.saniikos.backend.services.AuthService;
import com.saniikos.backend.authorization.LoginRequestDTO;
import com.saniikos.backend.authorization.LoginResponseDTO;
import com.saniikos.backend.authorization.UserInfo;
import com.saniikos.backend.authorization.security.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

        private final AuthenticationManager authenticationManager;
        private final UserRepository userRepository;
        private final JwtUtils jwtUtils;

        public AuthServiceImpl(
                        AuthenticationManager authenticationManager,
                        UserRepository userRepository,
                        JwtUtils jwtUtils) {

                this.authenticationManager = authenticationManager;
                this.userRepository = userRepository;
                this.jwtUtils = jwtUtils;
        }

        @Override
        public LoginResponseDTO login(LoginRequestDTO request) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                SecurityContextHolder.getContext()
                                .setAuthentication(authentication);

                UserInfo authenticatedUser = (UserInfo) authentication.getPrincipal();

                User user = userRepository.findByUsername(
                                authenticatedUser.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Set<String> roles = user.getUserRoles()
                                .stream()
                                .map(userRole -> userRole.getRole().getCode())
                                .collect(Collectors.toSet());

                UserInfo userInfo = new UserInfo(
                                user.getId(),
                                user.getUsername(),
                                null,
                                roles);

                user.setLastLogin(LocalDateTime.now());

                userRepository.save(user);

                try {

                        return jwtUtils.generateJwtTokens(userInfo);

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Error generating JWT token", e);
                }

        }

}