package com.gabeWebTest.webTest.security;

import com.gabeWebTest.webTest.filters.JWTAuthentificationFilter;
import com.gabeWebTest.webTest.services.JwtService;
import com.gabeWebTest.webTest.services.UserDetailsSer;
import com.gabeWebTest.webTest.views.dashboard.DashboardView;
//import com.gabeWebTest.webTest.views.security.LoginView;
//import com.gabeWebTest.webTest.views.security.upload.UploadView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestBody;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    private UserDetailsSer userDetailsService;

    @Autowired
    private JWTAuthentificationFilter jwtAuthentificationFilter;

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        //Data Access Object Provider
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        //set password encoder
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//
//        return provider;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(auth ->
            auth.requestMatchers(AntPathRequestMatcher
                            .antMatcher("/upload/**")).authenticated()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll())
                        .userDetailsService(userDetailsService)
                                .formLogin(form -> form
                                        // Specify the login page
                                        .successForwardUrl("/upload")
                .failureUrl("/login?error=true")) // Redirect to login-view with an error flag
                .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
//        setLoginView(http, LoginView.class);
}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth ->
//                auth.requestMatchers("/login/**","/register/**").permitAll()
//                        .requestMatchers("/upload/**").hasAuthority("ADMIN")
//                        .anyRequest()
//                        .authenticated())
//                .userDetailsService(userDetailsService)
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

    @Override
    protected void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(HttpMethod.POST,"/handleFadeOutCompletion","/login","/register");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
