package com.saniikos.backend.authorization.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.configurationSource(request -> {

            CorsConfiguration configuration = new CorsConfiguration();

            configuration.addAllowedOrigin("*");
            configuration.addAllowedMethod(HttpMethod.GET);
            configuration.addAllowedMethod(HttpMethod.POST);
            configuration.addAllowedMethod(HttpMethod.DELETE);
            configuration.addAllowedMethod(HttpMethod.PATCH);
            configuration.addAllowedMethod(HttpMethod.PUT);
            configuration.addAllowedMethod(HttpMethod.OPTIONS);
            configuration.setAllowedHeaders(List.of("*"));

            return configuration;
        })).csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(getAuthWhitelist()).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private String[] getAuthWhitelist() {
        List<String> authWhitelist = new ArrayList<>();

        authWhitelist.add("/users/login");
        authWhitelist.add("/api/departments/**");
        authWhitelist.add("/api/roles/**");
        authWhitelist.add("/api/tool-categories/**");
        authWhitelist.add("/api/tools/**");
        authWhitelist.add("/api/tool-visibilities/**");
        authWhitelist.add("/api/tool-departments/**");
        authWhitelist.add("/api/tool-permissions/**");
        authWhitelist.add("/api/users/**");
        authWhitelist.add("/api/user-roles/**");
        // TODO Revisar esto, ya que solo /users/login deberia ser publico, el resto
        // deberia requerir autenticacion y permisos de usuario

        return authWhitelist.toArray(String[]::new);
    }

}
