package com.saniikos.backend.autorization.security;

import com.saniikos.backend.autorization.LoginRequestDTO;
import com.saniikos.backend.autorization.UserInfo;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Check if the user exist
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authentication is null");
            }
            throw new AuthenticationServiceException("No authentication data provided");
        }

        LoginRequestDTO loginRequestDTO = (LoginRequestDTO) authentication.getPrincipal();
        String password = loginRequestDTO.getPassword();

        UserInfo user = getUserInfoByUsername(loginRequestDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User is not exist!");
        }

        String savedUserPassword = user.getPassword();

        if (!encoder.matches(password, savedUserPassword)) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRole() == null) {
            throw new BadCredentialsException("Authentication Failed. User granted authority is empty.");
        }

        List<GrantedAuthority> grantedAuthorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().toString().toUpperCase()));

        return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private UserInfo getUserInfoByUsername(String username) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        UserInfo userInfo = null;
        if (userOptional.isPresent()) {
            User userDto = userOptional.get();
            userInfo = new UserInfo(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
        }

        return userInfo;
    }
}
