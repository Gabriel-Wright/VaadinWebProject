package com.gabeWebTest.webTest.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends VaadinWebSecurity {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfiguration(JWTAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions().sameOrigin() // <-- allow H2 console to render in iframe
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/upload/**")
                .authenticated()
                .requestMatchers("/h2-console/**")
                .permitAll() // <-- allow H2 console
//                .requestMatchers("/","/about-me",
//                        "/article/**",
//                        "/what-is-this-site",
//                        "/api/v1/auth/**",
//                        "/handleFadeOutCompletion",
//                        // Vaadin Flow static resources //
//                        "/VAADIN/**",
//
//                        // the standard favicon URI
//                        "/favicon.ico",
//
//                        // the robots exclusion standard
//                        "/robots.txt",
//
//                        // web application manifest //
//                        "/manifest.webmanifest",
//                        "/sw.js",
//                        "/offline-page.html",
//
//                        // (development mode) static resources //
//                        "/frontend/**",
//
//                        // (development mode) webjars //
//                        "/webjars/**",
//
//                        // (production mode) static resources //
//                        "/frontend-es5/**", "/frontend-es6/**")
//                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
//                .formLogin()
//                .permitAll()
//                .loginPage("/login")
//                .defaultSuccessUrl("/upload")
//                .failureUrl("/login?error=true")
//                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Allows access to static resources, bypassing Spring security.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(
                //Icons
                "/icons/**",
                //Fonts
                "/font/**",
                //Images
                "/img/**",
                //PDFs
                "/pdf/**");
    }

}
