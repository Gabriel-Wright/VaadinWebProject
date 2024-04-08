package com.gabeWebTest.webTest.security;

import com.gabeWebTest.webTest.filters.JWTAuthentificationFilter;
import com.gabeWebTest.webTest.services.JwtService;
import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import com.gabeWebTest.webTest.views.security.LoginView;
import com.gabeWebTest.webTest.views.security.upload.UploadView;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestBody;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTAuthentificationFilter jwtAuthentificationFilter;

    @Bean
    AuthenticationProvider authenticationProvider() {
        //Data Access Object Provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //set password encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Enforce HTTPS globall
        http.requiresChannel().anyRequest().requiresSecure();

        http.addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //Allow access to all paths except from upload - am also able to post for handleFadeOut to allow UI to respawn.
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/handleFadeOutCompletion/")).permitAll().
                        requestMatchers(AntPathRequestMatcher.antMatcher("/upload/**")).authenticated().
                        requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll());
        http.formLogin(form ->
                form.defaultSuccessUrl("/upload").failureUrl("/login-view"));
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Override
    protected void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(HttpMethod.POST,"/handleFadeOutCompletion");

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
