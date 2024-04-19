package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.auth.AuthenticationRequest;
import com.gabeWebTest.webTest.data.auth.AuthenticationResponse;
import com.gabeWebTest.webTest.data.auth.RegisterRequest;
import com.gabeWebTest.webTest.data.users.Role;
import com.gabeWebTest.webTest.data.users.User;
import com.gabeWebTest.webTest.data.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JWTService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(
        request.getUsername(),
        passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(jwtToken);

        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.info("attempting to authenticate now");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        System.out.println("Authenticated through username password: given authentication token: "+
                jwtToken);
        AuthenticationResponse response = new AuthenticationResponse(jwtToken);

        return response;
    }
}
