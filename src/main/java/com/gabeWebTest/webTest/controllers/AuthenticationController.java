package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.auth.AuthenticationRequest;
import com.gabeWebTest.webTest.data.auth.AuthenticationResponse;
import com.gabeWebTest.webTest.data.auth.RegisterRequest;
import com.gabeWebTest.webTest.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    private final AuthenticationService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateJson (
            @RequestBody AuthenticationRequest request
    ) {
        logger.info("Attempting to authenticate:"+request.getUsername());
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // This method handles requests with "application/x-www-form-urlencoded" content type
    @PostMapping(value = "/authenticate", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<AuthenticationResponse> authenticateFormUrlEncoded(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        logger.info("Attempting to authenticate");
        // Create AuthenticationRequest object from form parameters
        AuthenticationRequest request = new AuthenticationRequest(username, password);

        // Call the existing authentication method
        return authenticateJson(request);
    }


}
