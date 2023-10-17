package br.com.atilara.authenticationjwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.atilara.authenticationjwt.user.PermissionEnum.*;
import static br.com.atilara.authenticationjwt.user.PermissionEnum.ADMIN_CREATE;
import static br.com.atilara.authenticationjwt.user.RoleEnum.ADMIN;
import static br.com.atilara.authenticationjwt.user.RoleEnum.MANAGER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**", "swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .requestMatchers(GET, "api/v1/management/**").hasAnyAuthority(MANAGER_READ.name(), ADMIN_READ.name())
                .requestMatchers(POST, "api/v1/management/**").hasAnyAuthority(MANAGER_CREATE.name(), ADMIN_CREATE.name())
                .requestMatchers(PUT, "api/v1/management/**").hasAnyAuthority(MANAGER_UPDATE.name(), ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "api/v1/management/**").hasAnyAuthority(MANAGER_DELETE.name(), ADMIN_DELETE.name())
                .requestMatchers("/api/v1/admin/**").hasAnyRole(ADMIN.name())
                .requestMatchers(GET, "api/v1/admin/**").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "api/v1/admin/**").hasAnyAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "api/v1/admin/**").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "api/v1/admin/**").hasAnyAuthority(ADMIN_DELETE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSec.build();
    }

}
