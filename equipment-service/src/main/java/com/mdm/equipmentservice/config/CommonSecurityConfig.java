package com.mdm.equipmentservice.config;

import com.mdm.equipmentservice.converter.AuthorityConverter;
import com.mdm.equipmentservice.filter.CsrfCookieFilter;
import com.mdm.equipmentservice.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class CommonSecurityConfig {

    private final List<String> allowedOrigins = List.of("http://localhost:3000", "http://localhost:8080", "http://localhost:3001");

    private final UserRepository userRepository;

    private final ApplicationContext context;

    @Autowired
    public CommonSecurityConfig(UserRepository userRepository, ApplicationContext context) {
        this.userRepository = userRepository;
        this.context = context;
    }

    @Bean(name = "corsConfigurationSource")
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(allowedOrigins);
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
            corsConfiguration.setExposedHeaders(List.of("Authorization"));
            corsConfiguration.setMaxAge(3600L);
            return corsConfiguration;
        };
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new AuthorityConverter(userRepository));
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .configurationSource(context.getBean("corsConfigurationSource", CorsConfigurationSource.class))
                .and()
                .csrf()
                .disable()
                .csrf(csrf -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers(
                                "/login",
                                "/register",
                                "/swagger-ui.html",
                                "/api/v1/users/reset-password/**"
                        ) //TODO: modify login and register endpoint
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/v1/users/reset-password/**"
//TODO: for development only, remove in production
                ).permitAll().anyRequest().authenticated())
                .authorizeHttpRequests()
                .and()
                .oauth2ResourceServer(configurer -> configurer.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter));
        return http.build();
    }

    @Bean
    public AuthorityConverter authorityConverter() {
        return new AuthorityConverter(userRepository);
    }
}
