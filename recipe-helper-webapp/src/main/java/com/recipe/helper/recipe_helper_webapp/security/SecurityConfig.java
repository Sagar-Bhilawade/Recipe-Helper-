package com.recipe.helper.recipe_helper_webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    //A bean method which will replace spring security chain by its own security chain
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(customizer->customizer.disable())
                        .authorizeHttpRequests(requests-> requests
                                .requestMatchers("/user/signup").permitAll()
                                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
    }

    @Bean
    public AuthenticationProvider customAuthentication()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);
        return  provider;
    }


}
