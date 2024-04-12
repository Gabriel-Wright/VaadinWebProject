package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.auth.AuthenticationRequest;
import com.gabeWebTest.webTest.data.auth.AuthenticationResponse;
import com.gabeWebTest.webTest.data.auth.RegisterRequest;
import com.gabeWebTest.webTest.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")

public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register (
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(authService.register(request));
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
