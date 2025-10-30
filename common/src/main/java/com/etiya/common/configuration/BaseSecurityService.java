package com.etiya.common.configuration;

import com.etiya.common.filters.JwtAuthFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;


@Service
public class BaseSecurityService {

    private final JwtAuthFilter  jwtAuthFilter;

    public BaseSecurityService(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    private static final String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v2/api-docs/**",
            "api/auth/**"
    }; //bunları filtreye dahil etme diyorum

    public HttpSecurity configureCoreSercurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req->req.requestMatchers(WHITE_LIST).permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }

}
