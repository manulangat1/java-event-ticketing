package com.example.ticket_platform.config;


import com.example.ticket_platform.filters.UserProvisioningFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http, UserProvisioningFilter userProvisioningFilter) throws  Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        // Whitelist /api-docs and Swagger UI endpoints
                        .requestMatchers(
                                "/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);

        return http.build();
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).csrf(
//                csrf -> csrf.disable()
//        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
//                        Customizer.withDefaults()
//                ) ).addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);
//        return http.build();
    }
}
