package com.jeral.socialmedia.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin().disable() //this will prevent username and password login of spring security
                .securityMatcher("/api/v1/**")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry

                        //In here we should give all the endpoints without authentication.
                        //Other endpoints should under jwt authentication

//                        .requestMatchers("/api/v1/post/").permitAll()
//                        .requestMatchers("/api/v1/post/**").permitAll()
//                        .requestMatchers("/api/v1/post/like/**").permitAll()
//                        .requestMatchers("/api/v1/post/dislike/**").permitAll()

//                        .requestMatchers("/api/v1/comment/").permitAll()
//                        .requestMatchers("/api/v1/comment/**").permitAll()

//                        .requestMatchers("/api/v1/admin/list").permitAll()
                        .requestMatchers("/api/v1/admin/auth/login").permitAll()
                        .requestMatchers("/api/v1/admin/auth/register").permitAll()
//                        .requestMatchers("/api/v1/admin/**").permitAll()

//                        .requestMatchers("/api/v1/user/").permitAll()
//                        .requestMatchers("/api/v1/user/**").permitAll()
                        .requestMatchers("/api/v1/user/auth/login").permitAll()
                        .requestMatchers("/api/v1/user/auth/register").permitAll()

                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
