package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.auth.AuthenticationRequest;
import com.gabeWebTest.webTest.data.auth.AuthenticationResponse;
import com.gabeWebTest.webTest.data.auth.RegisterRequest;
import com.gabeWebTest.webTest.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

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
    public ResponseEntity<AuthenticationResponse> authenticateJson (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // This method handles requests with "application/x-www-form-urlencoded" content type
    @PostMapping(value = "/authenticate", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<AuthenticationResponse> authenticateFormUrlEncoded(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        // Create AuthenticationRequest object from form parameters
        AuthenticationRequest request = new AuthenticationRequest(username, password);

        // Call the existing authentication method
        return authenticateJson(request);
    }


}
