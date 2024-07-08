package com.brieuc.dailymon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationConfig {

    private final JwtAuthFilter jwtAuthentificationFilter;
    private final AuthenticationProvider authenticationProvider;

     
    private static final String[] WHITE_LIST_URL = {
        "/auth/login",
        "/auth/register"
    };

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
            .csrf(csrfConfigurer -> csrfConfigurer.disable())
            .authorizeHttpRequests(authorize -> authorize.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated())
            .authenticationProvider(authenticationProvider)
            .sessionManagement(Customizer -> Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    



}
