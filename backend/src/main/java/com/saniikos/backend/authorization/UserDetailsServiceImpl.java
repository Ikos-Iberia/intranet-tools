package com.saniikos.backend.authorization;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saniikos.backend.entities.User;
import com.saniikos.backend.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username));


        Set<String> roles = user.getUserRoles()
                .stream()
                .map(userRole ->
                        userRole.getRole().getCode())
                .collect(Collectors.toSet());


        return new UserInfo(
                user.getId(),
                user.getUsername(),
                user.getPasswordHash(),
                roles
        );
    }

}