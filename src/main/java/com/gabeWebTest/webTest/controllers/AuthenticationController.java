package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.users.AuthenticationResponse;
import com.gabeWebTest.webTest.data.users.User;
import com.gabeWebTest.webTest.services.AuthenticationService;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(
            User request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login (
            User request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/login")
    public ResponseEntity<Object> redirectToLoginView() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/login-view");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
